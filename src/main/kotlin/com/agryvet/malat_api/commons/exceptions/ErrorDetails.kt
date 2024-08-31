package com.agryvet.malat_api.commons.exceptions

import java.time.LocalDateTime

data class ErrorDetails(
    val timestamp: LocalDateTime,
    val message: String?,
    val details: String?
)