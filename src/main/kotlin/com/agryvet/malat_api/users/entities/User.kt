package com.agryvet.malat_api.users.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class User(
    val id: Int,
    @JsonProperty("first_name")
    val name: String,
    val date: LocalDate
)
