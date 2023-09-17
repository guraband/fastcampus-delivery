package com.delivery.db.repository

import com.delivery.db.entity.User
import com.delivery.db.enums.UserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long, status: UserStatus?): User?

    fun findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
        email: String?,
        password: String?,
        status: UserStatus?
    ): User?
}