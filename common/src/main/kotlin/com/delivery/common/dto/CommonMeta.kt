package com.delivery.common.dto

import com.delivery.common.status.StatusCode
import com.delivery.common.status.SuccessStatusCode

class CommonMeta(
    private val code: Int,
    private val message: String,
    private val description: String? = null,
) {
    companion object {
        fun ok(): CommonMeta {
            return CommonMeta(
                SuccessStatusCode.OK.getCode(),
                SuccessStatusCode.OK.getMessage(),
                "성공",
            )
        }

        fun error(errorStatusCode: StatusCode): CommonMeta {
            return CommonMeta(
                errorStatusCode.getCode(),
                errorStatusCode.getMessage(),
            )
        }

        fun error(
            errorStatusCode: StatusCode,
            t: Throwable,
        ): CommonMeta {
            return CommonMeta(
                errorStatusCode.getCode(),
                errorStatusCode.getMessage(),
                description = t.localizedMessage
            )
        }

        fun error(
            errorStatusCode: StatusCode,
            description: String,
        ): CommonMeta {
            return CommonMeta(
                errorStatusCode.getCode(),
                errorStatusCode.getMessage(),
                description = description
            )
        }
    }
}