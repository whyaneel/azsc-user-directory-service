package com.prototype.userdirectory.repository

import com.prototype.userdirectory.model.UserDAO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserDAO, String> {
    @Query(
        value = "SELECT * FROM user_directory WHERE is_deleted = true ORDER BY first_name",
        nativeQuery = true
    )
    fun findAllInActive(): List<UserDAO>

    @Query(
        value = "SELECT * FROM user_directory WHERE is_deleted = true AND id = (:id)",
        nativeQuery = true
    )
    fun findInActiveById(@Param("id") id: String): Optional<UserDAO>

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM user_directory WHERE is_deleted = true AND id = (:id);" +
            "DELETE FROM address_directory WHERE user_id = (:id);",
        nativeQuery = true
    )
    fun permDelete(@Param("id") id: String)

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE user_directory SET is_deleted = true WHERE id = (:id)",
        nativeQuery = true
    )
    fun softDelete(@Param("id") id: String)

    @Query(
        "SELECT * FROM user_directory u INNER JOIN address_directory addr ON u.id = addr.user_id " +
            "where is_deleted = false " +
            "AND CONCAT(first_name, ' ', last_name) ILIKE %:name% " +
            "AND country = (:country) " +
            "ORDER BY first_name",
        nativeQuery = true
    )
    fun searchUsers(@Param("name") name: String, @Param("country") country: String): List<UserDAO>

    @Query(
        "SELECT * FROM user_directory u INNER JOIN address_directory addr ON u.id = addr.user_id " +
            "where is_deleted = true " +
            "AND CONCAT(first_name, ' ', last_name) ILIKE %:name% " +
            "AND country = (:country) " +
            "ORDER BY first_name",
        nativeQuery = true
    )
    fun searchInActiveUsers(@Param("name") name: String, @Param("country") country: String): List<UserDAO>
}