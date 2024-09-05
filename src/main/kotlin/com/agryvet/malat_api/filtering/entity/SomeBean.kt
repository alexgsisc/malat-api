package com.agryvet.malat_api.filtering.entity

import com.fasterxml.jackson.annotation.JsonIgnore

data class SomeBean(
    val field1: String,
    //Annotation for filter static
    @JsonIgnore
    val field2: String,
    val field3: String,
)
