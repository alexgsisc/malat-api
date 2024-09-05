package com.agryvet.malat_api.filtering.controller

import com.agryvet.malat_api.filtering.entity.SomeBean
import com.agryvet.malat_api.filtering.entity.SomeBeanDynamic
import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import org.springframework.http.converter.json.MappingJacksonValue
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

    @GetMapping("dynamic")
    fun dynamicFiltering(): MappingJacksonValue {
        val someBean = SomeBeanDynamic("value1", "value2", "value3")
        val mappingJacksonValue = MappingJacksonValue(someBean)
        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3")
        val filters: FilterProvider = SimpleFilterProvider().addFilter("some-bean-filter", filter)
        mappingJacksonValue.filters = filters

        return mappingJacksonValue
    }

    @GetMapping("dynamic-list")
    fun dynamicListFiltering(): MappingJacksonValue {
        val someBeanList = listOf(
            SomeBeanDynamic("value1", "value2", "value3"),
            SomeBeanDynamic("value4", "value5", "value6")
        )
        val mappingJacksonValue = MappingJacksonValue(someBeanList)
        val filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3")
        val filters: FilterProvider = SimpleFilterProvider().addFilter("some-bean-filter", filter)
        mappingJacksonValue.filters = filters

        return mappingJacksonValue
    }
}