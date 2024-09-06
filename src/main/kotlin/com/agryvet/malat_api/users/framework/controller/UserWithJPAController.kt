package com.agryvet.malat_api.users.framework.controller

import com.agryvet.malat_api.users.entities.Posts
import com.agryvet.malat_api.users.entities.User
import com.agryvet.malat_api.users.entities.exceptions.UserNotFoundException
import com.agryvet.malat_api.users.framework.controller.request.UserDto
import com.agryvet.malat_api.users.repository.UserRepository
import jakarta.validation.Valid
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Optional


@RestController
@RequestMapping(path = ["/jpa/users"])
class UserWithJPAController(private val repository: UserRepository) {

    @GetMapping
    fun getAllUsers(): List<User> {
        return repository.findAll()
    }

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Int): Optional<User> {
        val user: Optional<User> = repository.findById(userId)

        if (user.isEmpty) {
            throw UserNotFoundException("User not found with id: $userId")
        }

        return user
    }

    //WebMVCLinkBuilder
    @GetMapping("/retrieve/{userId}")
    fun retrieveUsersById(@PathVariable userId: Int): EntityModel<User> {
        val user: Optional<User> = repository.findById(userId)

        if (user.isEmpty) {
            throw UserNotFoundException("User not found with id: $userId")
        }
        val entityModel: EntityModel<User> = EntityModel.of(user.get())

        val linkBuilder: WebMvcLinkBuilder = WebMvcLinkBuilder.linkTo(methodOn(this::class.java).getAllUsers())

        entityModel.add(linkBuilder.withRel("all-users"))

        return entityModel
    }

    @PostMapping
    fun addUser(@Valid @RequestBody user: UserDto): ResponseEntity<User> {
        val saveUser = repository.save(User(id = null, name = user.name, date = user.date))

        val uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{userId}")
            .buildAndExpand(saveUser.id)
            .toUri()
        return ResponseEntity.created(uriLocation).build()
    }

    @DeleteMapping("/{userId}")
    fun deleteUserById(@PathVariable userId: Int) {
        repository.deleteById(userId)
    }

    @GetMapping("/{userId}/posts")
    fun getRetrievePostsForUser(@PathVariable userId: Int): List<Posts> {
        val user: Optional<User> = repository.findById(userId)

        if (user.isEmpty) {
            throw UserNotFoundException("User not found with id: $userId")
        }

        return user.get().posts ?: throw UserNotFoundException("User not found with id: $userId")
    }


}