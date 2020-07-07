package com.prototype.userdirectory.model

import com.prototype.userdirectory.utils.addCommaIfNeeded
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "user_directory")
@Where(clause = "is_deleted = false")
data class UserDAO(
    @Id
    @Column(name = "id")
    val id: String = "",

    @Column(name = "first_name")
    val firstName: String = "",

    @Column(name = "last_name")
    val lastName: String = "",

    @Column(name = "contact_number")
    val contactNumber: String = "",

    @Column(name = "is_deleted")
    var deleted: Boolean = false,

    @ElementCollection
    @CollectionTable(name = "address_directory", joinColumns = [JoinColumn(name = "user_id")])
    var addresses: List<AddressDAO>? = emptyList()
) {
    fun formatAddress() = addresses?.map { (line1, line2, city, country, postal) ->
        line1.addCommaIfNeeded() + line2.addCommaIfNeeded() + city.addCommaIfNeeded() + country + " " + postal
    }
}

