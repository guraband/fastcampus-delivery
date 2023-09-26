package com.delivery.account.controller

import com.delivery.account.business.TokenBusiness
import com.delivery.account.model.TokenValidationRequest
import com.delivery.account.model.TokenValidationResponse
import com.delivery.common.Log
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal-api/token")
class TokenInternalApiController(
    private val tokenBusiness: TokenBusiness,
) {
    companion object : Log

    @PostMapping("/validate")
    fun tokenValidation(
        @RequestBody request: TokenValidationRequest
    ): TokenValidationResponse {
        log.info("token validation :init : {}", request)
        return tokenBusiness.validateToken(request)
    }
}