package com.delivery.db.repository

import com.delivery.db.entity.Store
import com.delivery.db.enums.StoreCategory
import com.delivery.db.enums.StoreStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long> {
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long, status: StoreStatus): Store?

    fun findAllByStatusOrderByIdDesc(status: StoreStatus): List<Store>

    fun findAllByStatusAndCategoryOrderByStarDesc(status: StoreStatus, category: StoreCategory): List<Store>
}