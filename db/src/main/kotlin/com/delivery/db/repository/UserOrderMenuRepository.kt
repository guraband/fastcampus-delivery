package com.delivery.db.repository

import com.delivery.db.entity.UserOrderMenu
import com.delivery.db.enums.UserOrderMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderMenuRepository : JpaRepository<UserOrderMenu, Long> {
    fun findAllByUserOrderIdAndStatusOrderById(userOrderId: Long?, status: UserOrderMenuStatus?): List<UserOrderMenu>
}