package com.delivery.common.status

import org.springframework.http.HttpStatus

enum class SuccessStatusCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String,
) : StatusCode {
    OK(HttpStatus.OK.value(), 200, "성공")
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