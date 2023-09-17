package com.delivery.common.status

import org.springframework.http.HttpStatus

enum class ErrorStatusCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String,
) : StatusCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null point"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 521, "존재하지 않는 회원"),
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