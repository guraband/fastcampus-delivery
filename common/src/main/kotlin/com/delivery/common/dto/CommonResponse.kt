package com.delivery.common.dto

import com.delivery.common.status.StatusCode
import jakarta.validation.Valid

data class CommonResponse<T>(
    var commonMeta: CommonMeta,

    @field:Valid
    var body: T? = null
) {
    companion object {
        @JvmStatic
        fun <T> ok(): CommonResponse<T> {
            return CommonResponse(CommonMeta.ok())
        }

        @JvmStatic
        fun <T> ok(
            body: T
        ): CommonResponse<T> {
            return CommonResponse(CommonMeta.ok(), body)
        }

        @JvmStatic
        fun ok(
            meta: CommonMeta
        ): CommonResponse<Any> {
            return CommonResponse(meta)
        }

        @JvmStatic
        fun error(
            errorStatusCode: StatusCode
        ): CommonResponse<Any> {
            return CommonResponse(CommonMeta.error(errorStatusCode))
        }

        @JvmStatic
        fun error(
            errorStatusCode: StatusCode,
            t: Throwable,
        ): CommonResponse<Any> {
            return CommonResponse(CommonMeta.error(errorStatusCode, t))
        }

        @JvmStatic
        fun error(
            errorStatusCode: StatusCode,
            description: String,
        ): CommonResponse<Any> {
            return CommonResponse(CommonMeta.error(errorStatusCode, description))
        }
    }
}
