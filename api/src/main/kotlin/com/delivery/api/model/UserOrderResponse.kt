package com.delivery.api.model

import com.delivery.db.enums.UserOrderStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserOrderResponse(
    val id: Long? = null,
    val userId: Long? = null,
    val storeId: Long? = null,
    val status: UserOrderStatus? = null,
    val amount: BigDecimal? = null,
    val orderedAt: LocalDateTime? = null,
    val acceptedAt: LocalDateTime? = null,
    val cookingStartedAt: LocalDateTime? = null,
    val deliveryStartedAt: LocalDateTime? = null,
    val receivedAt: LocalDateTime? = null,
)