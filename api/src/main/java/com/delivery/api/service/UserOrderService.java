package com.delivery.api.service;

import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import com.delivery.db.entity.UserOrder;
import com.delivery.db.enums.UserOrderStatus;
import com.delivery.db.repository.UserOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public UserOrder getOrThrow(Long id, Long userId) {
        return userOrderRepository.findByIdAndStatusAndUserId(id, UserOrderStatus.ORDER, userId)
                .orElseThrow(() -> new ApiException(ErrorStatusCode.SERVER_ERROR));
    }

    public List<UserOrder> getUserOrders(Long userId) {
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.ORDER);
    }

    public List<UserOrder> getUserOrders(Long userId, List<UserOrderStatus> statuses) {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statuses);
    }

    // 현재 진행 중인 주문 목록
    public List<UserOrder> getCurrentUserOrders(Long userId) {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId,
                List.of(UserOrderStatus.ORDER,
                        UserOrderStatus.ACCEPT,
                        UserOrderStatus.COOKING,
                        UserOrderStatus.DELIVERY
                ));
    }

    // 과거 주문 목록
    public List<UserOrder> getFinishedUserOrders(Long userId) {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId,
                List.of(UserOrderStatus.RECEIVED));
    }

    private UserOrder changeStatus(UserOrder userOrder, UserOrderStatus status) {
        return Optional.ofNullable(userOrder)
                .map(item -> {
                    userOrder.changeStatus(status);
                    return userOrderRepository.save(userOrder);
                })
                .orElseThrow(() -> new ApiException(ErrorStatusCode.NULL_POINT));
    }

    // 주문
    public UserOrder order(UserOrder userOrder) {
        return changeStatus(userOrder, UserOrderStatus.ORDER);
    }

    // 주문 확인
    public UserOrder accept(UserOrder userOrder) {
        return changeStatus(userOrder, UserOrderStatus.ACCEPT);
    }

    // 조리 시작
    public UserOrder cooking(UserOrder userOrder) {
        return changeStatus(userOrder, UserOrderStatus.COOKING);
    }

    // 배달 시작
    public UserOrder delivery(UserOrder userOrder) {
        return changeStatus(userOrder, UserOrderStatus.DELIVERY);
    }

    // 배달 완료
    public UserOrder receive(UserOrder userOrder) {
        return changeStatus(userOrder, UserOrderStatus.RECEIVED);
    }
}
