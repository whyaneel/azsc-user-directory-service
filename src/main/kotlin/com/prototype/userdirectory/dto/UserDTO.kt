package com.prototype.userdirectory.dto

import com.prototype.userdirectory.api.validator.ValidUserId

data class UserDTO(
    @field:ValidUserId
    val id: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val contactNumber: String? = null,
    val addresses: List<String>? = null
)
