package com.delivery.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    private fun createAPIKeyScheme(): SecurityScheme {
        return SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
    }

    // https://www.baeldung.com/spring-boot-swagger-jwt 참고
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI().addSecurityItem(
            SecurityRequirement().addList("Bearer Authentication")
        )
            .components(
                Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())
            )
    }

    @Bean
    fun modelResolver(objectMapper: ObjectMapper): ModelResolver {
        return ModelResolver(objectMapper)
    }
}