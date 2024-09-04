package com.agryvet.malat_api.users.framework.service

import com.agryvet.malat_api.users.entities.User
import com.agryvet.malat_api.users.framework.controller.request.UserDto
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UsersService {
    private final var count = 0

    val users = mutableListOf(
        User(id = ++count, name = "Rosa", date = LocalDate.now().minusYears(14)),
        User(id = ++count, name = "Maria", date = LocalDate.now().minusYears(18)),
        User(id = ++count, name = "Jesus", date = LocalDate.now().minusYears(20)),
        User(id = ++count, name = "Luis", date = LocalDate.now().minusYears(20))
    )

    fun getAllUsers(): List<User> {
        return users
    }

    fun getUserById(id: Int): User? {
        return users.firstOrNull { it.id == id }
    }

    fun addUser(user: UserDto): User {
        val newUser = User(id = ++count, name = user.name, date = user.date)
        users.addLast(newUser)
        return newUser
    }

    fun deleteUser(userId: Int) {
        users.removeIf { it.id == userId }
    }
}