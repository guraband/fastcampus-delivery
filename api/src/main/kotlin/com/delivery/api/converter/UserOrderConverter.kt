package com.delivery.api.converter

import com.delivery.api.model.UserOrderResponse
import com.delivery.common.annotation.Converter
import com.delivery.db.entity.Store
import com.delivery.db.entity.StoreMenu
import com.delivery.db.entity.UserOrder

@Converter
class UserOrderConverter {
    fun toEntity(userId: Long, store: Store, storeMenus: List<StoreMenu>): UserOrder {
        var totalAmount = storeMenus
            .mapNotNull { it -> it.amount }
            .reduce { acc, bigDecimal -> acc.add(bigDecimal) }

        return UserOrder(
            userId = userId,
            store = store,
            amount = totalAmount,
            createdBy = userId,
        )
    }

    fun toResponse(entity: UserOrder): UserOrderResponse {
        return UserOrderResponse(
            id = entity.id,
            userId = entity.userId,
            storeId = entity.store?.id,
            amount = entity.amount,
            status = entity.status,
            orderedAt = entity.orderedAt,
        )
    }
}