package com.agryvet.malat_api.users.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotBlank

@Entity
data class Posts(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,

    @field:NotBlank(message = "Name is required")
    val description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    var user: User? = null
)