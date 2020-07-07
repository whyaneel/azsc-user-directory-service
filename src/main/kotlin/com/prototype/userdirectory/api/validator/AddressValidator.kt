package com.prototype.userdirectory.api.validator

import com.prototype.userdirectory.dto.AddressDTO
import org.springframework.stereotype.Component
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [AddressValidator::class])
annotation class ValidAddress(
    val message: String = "Address is not valid (postalCode: \${validatedValue.postalCode}, " +
        "country: \${validatedValue.country})",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

@Component
class AddressValidator(): ConstraintValidator<ValidAddress, AddressDTO> {
    override fun isValid(value: AddressDTO, ctx: ConstraintValidatorContext?): Boolean {
        if( value.country.isNullOrEmpty() || value.postalCode.isNullOrEmpty() ) {
            return false
        }
        return true
    }
}