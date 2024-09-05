package com.agryvet.malat_api.users.framework.controller

import com.agryvet.malat_api.users.entities.User
import com.agryvet.malat_api.users.entities.exceptions.UserNotFoundException
import com.agryvet.malat_api.users.framework.controller.request.UserDto
import com.agryvet.malat_api.users.framework.service.UsersService
import jakarta.validation.Valid
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
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
        val user = usersService.getUserById(userId) ?: throw UserNotFoundException("User not found with id: $userId")
        return user
    }

    @PostMapping
    fun addUser(@Valid @RequestBody user: UserDto): ResponseEntity<User> {
        val saveUser = usersService.addUser(user)

        val uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{userId}")
            .buildAndExpand(saveUser.id)
            .toUri()
        return ResponseEntity.created(uriLocation).build()
    }

    @DeleteMapping("/{userId}")
    fun deleteUserById(@PathVariable userId: Int) {
        usersService.deleteUser(userId)
    }

    //WebMVCLinkBuilder
    @GetMapping("/retrieve/{userId}")
    fun retrieveUsersById(@PathVariable userId: Int): EntityModel<User> {
        val user = usersService.getUserById(userId) ?: throw UserNotFoundException("User not found with id: $userId")
        val entityModel: EntityModel<User> = EntityModel.of(user)

        val linkBuilder: WebMvcLinkBuilder = WebMvcLinkBuilder.linkTo(methodOn(this::class.java).getAllUsers())

        entityModel.add(linkBuilder.withRel("all-users"))

        return entityModel
    }

}