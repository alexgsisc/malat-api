package com.agryvet.malat_api.users.repository

import com.agryvet.malat_api.users.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>