package com.delivery.common.dto

import java.time.LocalDateTime

data class Token(
    val token: String,
    val expiredAt: LocalDateTime? = null,
)
