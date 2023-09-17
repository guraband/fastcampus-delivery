package com.delivery.db.repository

import com.delivery.db.entity.UserOrder
import com.delivery.db.enums.UserOrderStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderRepository : JpaRepository<UserOrder, Long> {
    // 특정 유저의 모든 주문
    fun findAllByUserIdAndStatusOrderByIdDesc(userId: Long?, status: UserOrderStatus?): List<UserOrder>

    // 특정 유저의 특정 상태의 모든 주문
    fun findAllByUserIdAndStatusInOrderByIdDesc(userId: Long?, statuses: List<UserOrderStatus>?): List<UserOrder>

    // 특정 주문
    fun findByIdAndStatusAndUserId(id: Long?, status: UserOrderStatus?, userId: Long?): UserOrder?
}