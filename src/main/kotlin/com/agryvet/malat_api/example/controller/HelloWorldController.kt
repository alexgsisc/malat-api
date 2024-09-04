package com.agryvet.malat_api.example.controller


import com.agryvet.malat_api.example.model.HelloWorldBean
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContext
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HelloWorldController(private val messageSource: MessageSource) {

    @GetMapping("hello-world")
    fun helloWorld(): String {
        return "Hello world spring"
    }

    @GetMapping("hello-word-bean")
    fun helloWorldBean(): HelloWorldBean {
        return HelloWorldBean("Hello world bean")
    }

    @GetMapping("hello-word-path-variables/{name}")
    fun helloWorldPathVariables(@PathVariable name: String): HelloWorldBean {
        return HelloWorldBean(String.format("Hello world, %s", name))
    }

    @GetMapping("hello-world-internationalized")
    fun helloWorldInternationalized(): String {
        val locale: Locale = LocaleContextHolder.getLocale()
        println("the located is: $locale")
        val message = messageSource.getMessage("good.morning.message", null, "Default Message", locale)
        return message!!
    }
}