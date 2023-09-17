package com.delivery.common.status

import org.springframework.http.HttpStatus

enum class UserErrorStatusCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String,
) : StatusCode {
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 1001, "존재하지 않는 회원"),
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