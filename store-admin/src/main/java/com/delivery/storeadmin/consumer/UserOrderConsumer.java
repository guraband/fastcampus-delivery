package com.delivery.storeadmin.consumer;

import com.delivery.common.config.RabbitMqConfig;
import com.delivery.common.dto.message.UserOrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserOrderConsumer {

    @RabbitListener(queues = RabbitMqConfig.DEFAULT_QUEUE_NAME)
    public void userOrderConsumer(UserOrderMessage userOrderMessage) {
        log.info("message queue >> {}", userOrderMessage);
    }
}
