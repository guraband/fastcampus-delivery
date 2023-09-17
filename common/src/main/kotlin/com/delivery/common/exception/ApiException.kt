package com.delivery.common.exception

import com.delivery.common.status.ErrorStatusCode
import com.delivery.common.status.StatusCode

class ApiException : RuntimeException {

    val errorStatusCode: StatusCode
    val errorMessage: String

    companion object {
        @JvmStatic
        fun nullError(): ApiException {
            return ApiException(ErrorStatusCode.NULL_POINT)
        }
    }

    constructor(errorStatusCode: StatusCode) : super(errorStatusCode.getMessage()) {
        this.errorStatusCode = errorStatusCode
        errorMessage = errorStatusCode.getMessage()
    }

    constructor(
        errorStatusCode: StatusCode,
        message: String,
    ) : super(errorStatusCode.getMessage()) {
        this.errorStatusCode = errorStatusCode
        this.errorMessage = message
    }

    constructor(
        errorStatusCode: StatusCode,
        t: Throwable,
    ) : super(t) {
        this.errorStatusCode = errorStatusCode
        this.errorMessage = errorStatusCode.getMessage()
    }

}