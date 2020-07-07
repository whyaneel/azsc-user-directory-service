package com.prototype.userdirectory.dto

import com.prototype.userdirectory.api.validator.ValidAddress

@ValidAddress
data class AddressDTO(
    val addressLine1: String? = null,
    val addressLine2: String? = null,
    val city: String? = null,
    val country: String = "",
    val postalCode: String = ""
)