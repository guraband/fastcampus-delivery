package com.delivery.api.producer;

import com.delivery.common.dto.message.UserOrderMessage;
import com.delivery.common.rabbitmq.Producer;
import com.delivery.db.entity.UserOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
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
