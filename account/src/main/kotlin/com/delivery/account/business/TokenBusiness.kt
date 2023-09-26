package com.delivery.account.business

import com.delivery.account.model.TokenValidationRequest
import com.delivery.account.model.TokenValidationResponse
import com.delivery.account.service.TokenService
import com.delivery.common.annotation.Business

@Business
class TokenBusiness(
    private val tokenService: TokenService
) {
    fun validateToken(request: TokenValidationRequest): TokenValidationResponse {
        val result = tokenService.getIdOrThrow(request.token.token)

        return TokenValidationResponse(
            id = result
        )
    }
}
