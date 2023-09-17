package com.delivery.db.repository

import com.delivery.db.entity.StoreUser
import com.delivery.db.enums.StoreUserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreUserRepository : JpaRepository<StoreUser, Long> {
    fun findFirstByEmailAndStatusOrderByIdDesc(email: String, status: StoreUserStatus): StoreUser?
}