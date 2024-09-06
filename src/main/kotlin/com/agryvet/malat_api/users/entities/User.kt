package com.agryvet.malat_api.users.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDate

@Entity(name = "user_details")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    @JsonProperty("first_name")
    val name: String,
    val date: LocalDate,

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    val posts: List<Posts>? = null
)
