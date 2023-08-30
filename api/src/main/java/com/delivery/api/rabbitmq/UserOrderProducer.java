package com.delivery.api.rabbitmq;

import com.delivery.common.dto.message.UserOrderMessage;
import com.delivery.db.entity.UserOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderProducer {
    private final Producer producer;

    public void sendOrder(UserOrder userOrder) {
        sendOrder(userOrder.getId());
    }

    public void sendOrder(Long userOrderId) {
        var message = UserOrderMessage.builder()
                .userOrderId(userOrderId)
                .build();

        producer.producer(message);
    }
}
