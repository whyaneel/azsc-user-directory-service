package com.prototype.userdirectory.service

import com.prototype.userdirectory.NotFoundException
import com.prototype.userdirectory.dto.AddressDTO
import com.prototype.userdirectory.dto.UserDTO
import com.prototype.userdirectory.model.UserDAO
import com.prototype.userdirectory.repository.UserRepository
import com.prototype.userdirectory.utils.fromDTO
import com.prototype.userdirectory.utils.toDTO
import org.springframework.stereotype.Service
import javax.validation.ValidationException

@Service
class UserDirectoryService(
    private val userRepository: UserRepository
) {
    fun getInActiveUsers(): List<UserDTO> = userRepository.findAllInActive().map { it.toDTO() }

    fun getInActiveUser(id: String): UserDTO = inActiveUserOrThrow(id).toDTO()

    fun deleteInActiveUser(id: String) = userRepository.permDelete(inActiveUserOrThrow(id).id)

    fun getAllUsers(): List<UserDTO> =
        userRepository.findAll().map { it.toDTO() }

    //TODO search can be SearchCriteria
    //TODO Specification approach will have greater control - time factor
    fun searchUsers(search: String?): List<UserDTO> {
        search?.let {
            val (name, country) = searchCriteria(search)
            return userRepository.searchUsers(name.trim(), country.trim()).map { it.toDTO() }
        }
        return getAllUsers()
    }

    fun searchInActiveUsers(search: String?): List<UserDTO> {
        search?.let {
            val (name, country) = searchCriteria(search)
            return userRepository.searchInActiveUsers(name, country).map { it.toDTO() }
        }
        return getInActiveUsers()
    }

    fun getUser(id: String): UserDTO = userOrThrow(id).toDTO()

    fun postUser(user: UserDTO): UserDTO {
        val userDAO = user.fromDTO()
        return userRepository.save(userDAO).toDTO()
    }

    fun updateAddress(id: String, address: AddressDTO): UserDTO {
        val userDAO = userOrThrow(id)
        userDAO.apply {
            addresses = addresses?.plus(address.fromDTO())
        }
        return userRepository.save(userDAO).toDTO()
    }

    fun deleteUser(id: String) = userRepository.softDelete(userOrThrow(id).id)

    private fun userOrThrow(id: String): UserDAO {
        val userDAO = userRepository.findById(id)
            .orElseThrow { NotFoundException("No User Profile found [$id]") }
        return userDAO
    }

    private fun inActiveUserOrThrow(id: String): UserDAO {
        val userDAO = userRepository.findInActiveById(id)
            .orElseThrow { NotFoundException("No InActive User Profile found [$id]") }
        return userDAO
    }

    private fun searchCriteria(search: String): Pair<String, String> {
        var (name, country) = Pair(search.trim(), "Singapore")
        val SEARCH_DELIMITED = ","
        if (search.contains(SEARCH_DELIMITED)) {
            name = search.split(SEARCH_DELIMITED)[0].trim()
            country = search.split(SEARCH_DELIMITED)[1].trim()
        }
        if (name.isEmpty()) throw ValidationException("Search: Name can't be empty")
        if (country.isEmpty()) throw ValidationException("Search: Country can't be empty explicitly")
        return Pair(name, country)
    }

    //TODO Unit Test
}