package com.agryvet.malat_api.controller


import com.agryvet.malat_api.model.HelloWorldBean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("hello-world")
    fun helloWorld(): String {
        return  "Hello world spring"
    }

    @GetMapping("hello-word-bean")
    fun helloWorldBean(): HelloWorldBean {
        return HelloWorldBean("Hello world bean")
    }

    @GetMapping("hello-word-path-variables/{name}")
    fun helloWorldPathVariables(@PathVariable name:String): HelloWorldBean {
        return HelloWorldBean(String.format("Hello world, %s", name))
    }
}