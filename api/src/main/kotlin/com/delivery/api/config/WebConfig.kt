package com.delivery.api.config

import com.delivery.api.interceptor.AuthorizationInterceptor
import com.delivery.common.resolver.UserSessionResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val authorizationInterceptor: AuthorizationInterceptor,
    private val userSessionResolver: UserSessionResolver,
) : WebMvcConfigurer {
    private val OPEN_API = listOf(
        "/open-api/**"
    )

    private val DEFAULT_EXCLUDE = listOf(
        "/",
        "/health",
        "favicon.ico",
        "/apple-touch-icon.png",
        "/error"
    )

    private val SWAGGER = listOf(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    )

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationInterceptor)
            .excludePathPatterns(OPEN_API)
            .excludePathPatterns(DEFAULT_EXCLUDE)
            .excludePathPatterns(SWAGGER)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userSessionResolver)
    }
}