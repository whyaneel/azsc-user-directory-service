package com.prototype.userdirectory.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class AddressDAO(
    @Column(name = "address_line1")
    val addressLine1: String? = "",

    @Column(name = "address_line2")
    val addressLine2: String? = "",

    @Column(name = "city")
    val city: String? = "",

    @Column(name = "country")
    val country: String = "",

    @Column(name = "postal_code")
    val postalCode: String = ""
)