package com.delivery.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@ComponentScan(basePackages = ["com.delivery"])
@EnableJpaAuditing
@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}