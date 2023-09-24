package com.delivery.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/open-api/health")
class HealthCheckController {
    @GetMapping("")
    fun health(): String {
        return "OK";
    }
}