package com.prototype.userdirectory

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.reflect.UndeclaredThrowableException
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(
        ex: ConstraintViolationException,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = ex.constraintViolations.mapNotNull {
            ErrorResponseBody(message = it.message)
        }
        return handleErrorResponse(ex, Errors(errors), HttpStatus.BAD_REQUEST, request)
    }

    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(ex: ValidationException, request: WebRequest) =
        handleErrorResponse(ex, ex.toErrors(), HttpStatus.BAD_REQUEST, request)

    @ExceptionHandler(InternalServerError::class)
    fun handleInternalServerErrorException(ex: InternalServerError, request: WebRequest) =
        handleErrorResponse(ex, ex.toErrors(), HttpStatus.INTERNAL_SERVER_ERROR, request)

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest) =
        handleErrorResponse(ex, ex.toErrors(), HttpStatus.NOT_FOUND, request)

    @ExceptionHandler(RuntimeException::class)
    fun handleGenericException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val cause = getCause(ex)
        return handleErrorResponse(cause, cause.toErrors(), HttpStatus.INTERNAL_SERVER_ERROR, request)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = ex.bindingResult.allErrors.mapNotNull {
            if (it is FieldError) ErrorResponseBody(message = it.field + ": " + it.defaultMessage)
            else ErrorResponseBody(message = it.defaultMessage)
        }
        return handleErrorResponse(ex, Errors(errors), status, request)
    }

    private fun handleErrorResponse(
        ex: Exception,
        errors: Errors,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        return handleExceptionInternal(ex, errors, headers, status, request)
    }

    private fun getCause(ex: Exception): Exception {
        return if (ex is UndeclaredThrowableException && ex.cause != null) ex.cause as Exception else ex
    }
}

fun Exception.toErrors(): Errors = Errors(ErrorResponseBody(message = this.message))
data class Errors(val errors: List<ErrorResponseBody>) {
    constructor(error: ErrorResponseBody) : this(listOf(error))
}
data class ErrorResponseBody(val message: String?)
class InternalServerError(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
class NotFoundException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
