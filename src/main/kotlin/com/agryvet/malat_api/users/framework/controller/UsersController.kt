package com.agryvet.malat_api.users.framework.controller

import com.agryvet.malat_api.users.entities.User
import com.agryvet.malat_api.users.framework.controller.request.UserDto
import com.agryvet.malat_api.users.framework.service.UsersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping
    fun getAllUsers(): List<User> {
        return usersService.getAllUsers()
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Int): User {
        println("")
        return usersService.getUserById(userId)
    }

    @PostMapping
    fun addUser(@RequestBody user: UserDto): ResponseEntity<User> {
        val saveUser = usersService.addUser(user)

        val uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{userId}")
            .buildAndExpand(saveUser.id)
            .toUri()
        return ResponseEntity.created(uriLocation).build()
    }
}