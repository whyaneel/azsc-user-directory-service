package com.prototype.userdirectory.api.validator

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.reflect.KClass

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = arrayOf())
@NotNull
@NotBlank
@Size(message = "should be minimum {min} characters", min = 9)
annotation class ValidUserId(
    val message: String = "UserId is not valid (\${validatedValue})",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
