package com.agryvet.malat_api.users.framework.controller

import com.agryvet.malat_api.users.entities.Posts
import com.agryvet.malat_api.users.entities.exceptions.UserNotFoundException
import com.agryvet.malat_api.users.repository.PostsRepository
import com.agryvet.malat_api.users.repository.UserRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(path = ["/jpa/users"])
class PostsController(private val postRepository: PostsRepository, private val userRepository: UserRepository) {

    @PostMapping("/{userId}/posts")
    fun createPostsForUser(@PathVariable userId: Int, @Valid @RequestBody post: Posts): ResponseEntity<Posts> {
        val users = userRepository.findById(userId)

        if (users.isEmpty) {
            throw UserNotFoundException("User not found with id: $userId")
        }
        post.user = users.get()

        val savePosts = postRepository.save(post)

        val uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savePosts.id)
            .toUri()

        return ResponseEntity.created(uriLocation).build()
    }

    @GetMapping("/{userId}/posts/{postId}")
    fun getPostsForUser(@PathVariable userId: Int, @PathVariable postId: Int): Posts {

        val post = postRepository.findById(postId)
        if (post.isEmpty) {
            throw UserNotFoundException("Post not found with id: $postId")
        }
        if (post.get().user?.id != userId) {
            throw UserNotFoundException("Post not found with id: $postId and user id: $userId")
        }

        return post.get()
    }

}