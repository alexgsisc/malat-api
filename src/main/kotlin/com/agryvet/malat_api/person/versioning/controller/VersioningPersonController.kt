package com.agryvet.malat_api.person.versioning.controller

import com.agryvet.malat_api.person.versioning.entity.PersonV1
import com.agryvet.malat_api.person.versioning.entity.PersonV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class VersioningPersonController {

    @GetMapping("/v1/person")
    fun getFirstVersion(): PersonV1 {
        return PersonV1("Bob Charlie")
    }

    @GetMapping("/v2/person")
    fun getSecondVersion(): PersonV2 {
        return PersonV2("Bob", "Charlie")
    }

    @GetMapping(path = ["/person"], params = ["version=1"])
    fun getFirstVersionByParameter(): PersonV1 {
        return PersonV1("Bob Charlie")
    }

    @GetMapping(path = ["/person"], params = ["version=2"])
    fun getSecondVersionByParameter(): PersonV2 {
        return PersonV2("Bob", "Charlie")
    }

    @GetMapping(path = ["/person/header"], headers = ["X-API-VERSION=1"])
    fun getFirstVersionByRequestHeader(): PersonV1 {
        return PersonV1("Bob Charlie")
    }

    @GetMapping(path = ["/person/header"], headers = ["X-API-VERSION=2"])
    fun getSecondVersionByRequestHeader(): PersonV2 {
        return PersonV2("Bob", "Charlie")
    }


    @GetMapping(path = ["/person/accept"], produces = ["application/vnd.name-company.app-v1+json"])
    fun getFirstVersionByAcceptHeader(): PersonV1 {
        return PersonV1("Bob Charlie")
    }

    @GetMapping(path = ["/person/accept"], produces = ["application/vnd.name-company.app-v2+json"])
    fun getSecondVersionByAcceptHeader(): PersonV2 {
        return PersonV2("Bob", "Charlie")
    }

}