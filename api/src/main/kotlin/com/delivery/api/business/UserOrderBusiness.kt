package com.delivery.api.business

import com.delivery.api.converter.StoreConverter
import com.delivery.api.converter.StoreMenuConverter
import com.delivery.api.converter.UserOrderConverter
import com.delivery.api.converter.UserOrderMenuConverter
import com.delivery.api.model.UserOrderDetailResponse
import com.delivery.api.model.UserOrderRequest
import com.delivery.api.model.UserOrderResponse
import com.delivery.api.producer.UserOrderProducer
import com.delivery.api.service.StoreMenuService
import com.delivery.api.service.UserOrderMenuService
import com.delivery.api.service.UserOrderService
import com.delivery.common.Log
import com.delivery.common.annotation.Business
import com.delivery.common.exception.ApiException
import com.delivery.db.entity.UserOrder

@Business
class UserOrderBusiness(
    private val userOrderConverter: UserOrderConverter,
    private val userOrderMenuConverter: UserOrderMenuConverter,
    private val storeMenuConverter: StoreMenuConverter,
    private val storeConverter: StoreConverter,

    private val userOrderService: UserOrderService,
    private val userOrderMenuService: UserOrderMenuService,
    private val storeMenuService: StoreMenuService,

    private val userOrderProducer: UserOrderProducer,
) {
    companion object : Log

    fun order(
        request: UserOrderRequest,
        userId: Long,
    ): UserOrderResponse {
        var storeMenus = storeMenuService.getAllByIds(request.storeMenuIds)
        if (storeMenus.isEmpty() || storeMenus.size != request.storeMenuIds.size) {
            throw ApiException.nullError()
        }

        var userOrder = userOrderService.order(
            userOrderConverter.toEntity(userId, storeMenus.get(0).store, storeMenus)
        )

        var userOrderMenus = storeMenus.mapNotNull { storeMenu ->
            userOrderMenuConverter.toEntity(userOrder, storeMenu)
        }.toList()

        userOrderMenuService.order(userOrderMenus)

        userOrderProducer.sendOrder(userOrder)

        return userOrderConverter.toResponse(userOrder)
    }

    fun current(userId: Long): List<UserOrderDetailResponse> {
        return userOrderService.getUserOrders(userId)
            .run {
                toUserOrderDetailResponses(this)
            }
    }

    fun history(userId: Long): List<UserOrderDetailResponse> {
        return userOrderService.getFinishedUserOrders(userId)
            .run {
                toUserOrderDetailResponses(this)
            }
    }

    fun detail(userId: Long, orderId: Long): UserOrderDetailResponse {
        return userOrderService.getOrThrow(orderId, userId)
            .run {
                toUserOrderDetailResponse(this)
            }
    }

    private fun toUserOrderDetailResponse(userOrder: UserOrder): UserOrderDetailResponse {
        val userOrderMenus = userOrderMenuService.getUserOrderMenus(userOrder.id)
        val storeMenus = userOrderMenus.map { userOrderMenu ->
            userOrderMenu.storeMenu.id
        }.toList()
            .run {
                storeMenuService.getAllByIds(this)
            }

        return UserOrderDetailResponse(
            userOrderResponse = userOrderConverter.toResponse(userOrder),
            storeResponse = storeConverter.toResponse(storeMenus[0].store),
            storeMenuResponses = storeMenuConverter.toResponse(storeMenus),
        )
    }

    private fun toUserOrderDetailResponses(userOrders: List<UserOrder>): List<UserOrderDetailResponse> {
        return userOrders.map {
            toUserOrderDetailResponse(it)
        }.toList()
    }
}