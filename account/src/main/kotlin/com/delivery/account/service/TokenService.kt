package com.delivery.account.service

import com.delivery.account.component.JwtTokenHelper
import com.delivery.account.model.Token
import com.delivery.common.exception.ApiException
import com.delivery.common.status.ErrorStatusCode
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val jwtTokenHelper: JwtTokenHelper
) {
    fun issueAccessToken(id: Long?): Token? {
        return id?.let {
            val data = mapOf("id" to id)
            jwtTokenHelper.issueAccessToken(data)
        }
    }

    fun issueRefreshToken(id: Long?): Token? {
        requireNotNull(id)

        return id.let {
            val data = mapOf("id" to id)
            jwtTokenHelper.issueRefreshToken(data)
        }
    }

    fun getIdOrThrow(token: String?): Long? {
        return token?.let { it ->
            jwtTokenHelper.validate(it)
        }?.let { map ->
            map["id"]
        }?.toString()?.toLong()
            ?: throw ApiException(ErrorStatusCode.NULL_POINT)
    }
}