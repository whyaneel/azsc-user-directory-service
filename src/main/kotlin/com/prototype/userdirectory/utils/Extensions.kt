package com.prototype.userdirectory.utils

import com.prototype.userdirectory.dto.AddressDTO
import com.prototype.userdirectory.dto.UserDTO
import com.prototype.userdirectory.model.AddressDAO
import com.prototype.userdirectory.model.UserDAO

fun UserDAO.toDTO() = UserDTO(id, firstName, lastName, contactNumber, formatAddress())
fun UserDTO.fromDTO() = UserDAO(id, firstName!!, lastName!!, contactNumber!!)
fun AddressDTO.fromDTO() = AddressDAO(addressLine1, addressLine2, city, country, postalCode)
fun String?.addCommaIfNeeded() = takeIf { !it.isNullOrEmpty() }?.let { v -> "$v, " }.orEmpty()
