package com.delivery.common.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ObjectMapperConfig {

    @Bean
    open fun objectMapper(): ObjectMapper {
        var kotlinModule = KotlinModule.Builder().apply {
            withReflectionCacheSize(512)
            configure(KotlinFeature.NullToEmptyCollection, false)
            configure(KotlinFeature.NullToEmptyMap, false)
            configure(KotlinFeature.NullIsSameAsDefault, false)
            configure(KotlinFeature.SingletonSupport, false)
            configure(KotlinFeature.StrictNullChecks, false)
        }.build()

        var objectMapper = ObjectMapper().apply {
            registerModule(Jdk8Module())
            registerModule(JavaTimeModule())
            registerModule(kotlinModule)

            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }

        return objectMapper
    }
}