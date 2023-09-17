package com.delivery.db.repository

import com.delivery.db.entity.StoreMenu
import com.delivery.db.enums.StoreMenuStatus
import org.springframework.data.jpa.repository.JpaRepository

interface StoreMenuRepository : JpaRepository<StoreMenu, Long> {
    // 유효한 메뉴 1개
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long, status: StoreMenuStatus): StoreMenu?

    // 메뉴 여러개 조회
    fun findAllByIdInAndStatus(id: List<Long>, status: StoreMenuStatus): List<StoreMenu>

    // 특정 가게의 메뉴
    fun findAllByStoreIdAndStatusOrderBySortOrder(storeId: Long, status: StoreMenuStatus): List<StoreMenu>
}