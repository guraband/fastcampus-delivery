package com.delivery.api.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["com.delivery.db"])
@EnableJpaRepositories(basePackages = ["com.delivery.db"])
class JpaConfig {
}