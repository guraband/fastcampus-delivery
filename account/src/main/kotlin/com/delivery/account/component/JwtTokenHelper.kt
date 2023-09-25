package com.delivery.account.component

import com.delivery.account.model.Token

interface JwtTokenHelper {
    fun issueAccessToken(data: Map<String, Any>): Token?
    fun issueRefreshToken(data: Map<String, Any>): Token?
    fun validate(token: String): Map<String, Any>?
}