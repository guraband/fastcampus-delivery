package com.delivery.account.business

import com.delivery.account.service.TokenService
import com.delivery.common.annotation.Business
import com.delivery.common.dto.TokenValidationRequest
import com.delivery.common.dto.TokenValidationResponse

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
