package com.agryvet.malat_api.users.framework.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import java.time.LocalDate

data class UserDto(
    @field:NotBlank(message = "Name is required")
    val name: String,
    @field:Past(message = "the birth date should be in the past")
    val date: LocalDate)
