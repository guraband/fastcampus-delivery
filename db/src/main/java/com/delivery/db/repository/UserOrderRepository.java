package com.delivery.db.repository;

import com.delivery.db.entity.UserOrder;
import com.delivery.db.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    // 특정 유저의 모든 주문
    List<UserOrder> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    // 특정 유저의 특정 상태의 모든 주문
    List<UserOrder> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> statuses);

    // 특정 주문
    Optional<UserOrder> findByIdAndStatusAndUserId(Long id, UserOrderStatus status, Long userId);
}
