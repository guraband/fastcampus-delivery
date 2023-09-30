package com.delivery.common.status

import org.springframework.http.HttpStatus

enum class TokenErrorStatusCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String,
) : StatusCode {
    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), 2001, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST.value(), 2002, "만료된 토큰"),
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 2003, "토큰 없음"),
    TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST.value(), 2004, "토큰 오류"),
    ;

    override fun getHttpStatusCode(): Int {
        return this.httpStatusCode
    }

    override fun getCode(): Int {
        return this.code
    }

    override fun getMessage(): String {
        return this.message
    }
}