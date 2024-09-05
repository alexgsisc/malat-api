package com.agryvet.malat_api.filtering.entity

import com.fasterxml.jackson.annotation.JsonFilter

@JsonFilter("some-bean-filter")
data class SomeBeanDynamic(
    val field1: String,
    val field2: String,
    val field3: String,
)
