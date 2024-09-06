package com.agryvet.malat_api.users.repository

import com.agryvet.malat_api.users.entities.Posts
import org.springframework.data.jpa.repository.JpaRepository

interface PostsRepository : JpaRepository<Posts, Int>