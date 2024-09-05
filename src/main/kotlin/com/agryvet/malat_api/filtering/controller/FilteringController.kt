package com.agryvet.malat_api.filtering.controller

import com.agryvet.malat_api.filtering.entity.SomeBean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/filtering")
class FilteringController {

    @GetMapping
    fun getAllSomeBean(): SomeBean {
        return SomeBean("value1", "value2", "value3")
    }

    @GetMapping("/list")
    fun filteringList(): List<SomeBean> {
        return listOf(
            SomeBean("value1", "value2", "value3"),
            SomeBean("value4", "value5", "value6")
        )
    }
}