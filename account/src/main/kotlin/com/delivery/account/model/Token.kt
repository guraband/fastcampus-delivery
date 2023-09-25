package com.delivery.account.model

import java.time.LocalDateTime

data class Token(
    val token: String,
    val expiredAt: LocalDateTime,
)
