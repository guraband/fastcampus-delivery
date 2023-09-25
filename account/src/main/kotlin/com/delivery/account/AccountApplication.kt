package com.delivery.account

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class AccountApplication

fun main(args: Array<String>) {
    runApplication<AccountApplication>(*args)
}
