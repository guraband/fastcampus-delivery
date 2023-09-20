package com.delivery.db.entity

import com.delivery.db.enums.UserOrderStatus
import com.delivery.db.enums.UserOrderStatus.*
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
class UserOrder(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:Column(nullable = false)
    var userId: Long? = null,

    @field:JoinColumn(nullable = false)
    @field:ManyToOne
    var store: Store? = null,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: UserOrderStatus? = null,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var amount: BigDecimal? = null,

    @field:Column
    var orderedAt: LocalDateTime? = null,

    @field:Column
    var acceptedAt: LocalDateTime? = null,

    @field:Column
    var cookingStartedAt: LocalDateTime? = null,

    @field:Column
    var deliveryStartedAt: LocalDateTime? = null,

    @field:Column
    var receivedAt: LocalDateTime? = null,

    @field:OneToMany(mappedBy = "userOrder")
    @field:JsonIgnore
    var userOrderMenus: MutableList<UserOrderMenu>? = null,

    @field:Column
    var createdBy: Long? = null,
) {
    fun changeStatus(status: UserOrderStatus) {
        this.status = status

        when (status) {
            ORDER -> this.orderedAt = LocalDateTime.now()
            COOKING -> this.cookingStartedAt = LocalDateTime.now()
            DELIVERY -> this.deliveryStartedAt = LocalDateTime.now()
            RECEIVED -> this.receivedAt = LocalDateTime.now()
            ACCEPT -> this.acceptedAt = LocalDateTime.now()
            else -> {}
        }
    }
}