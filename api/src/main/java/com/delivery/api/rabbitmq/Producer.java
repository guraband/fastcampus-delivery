package com.delivery.api.rabbitmq;

import com.delivery.api.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    public void producer(String exchange, String routeKey, Object object) {
        rabbitTemplate.convertAndSend(exchange, routeKey, object);
    }

    public void producer(Object object) {
        producer(RabbitMqConfig.DEFAULT_EXCHANGE_NAME, RabbitMqConfig.DEFAULT_ROUTING_KEY_NAME, object);
    }
}
