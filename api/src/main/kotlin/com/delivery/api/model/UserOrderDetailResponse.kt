package com.delivery.api.model

data class UserOrderDetailResponse(
    val userOrderResponse: UserOrderResponse? = null,
    val storeResponse: StoreResponse? = null,
    val storeMenuResponses: List<StoreMenuResponse>? = null,
)
