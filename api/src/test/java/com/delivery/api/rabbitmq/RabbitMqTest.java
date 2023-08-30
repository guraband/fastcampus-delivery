package com.delivery.api.rabbitmq;

import com.delivery.api.config.RabbitMqConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("local")
public class RabbitMqTest {
    @Autowired
    Producer producer;

    @Test
    void 메시지_전송_테스트() {
        producer.producer(RabbitMqConfig.DEFAULT_EXCHANGE_NAME,
                RabbitMqConfig.DEFAULT_ROUTING_KEY_NAME,
                "Test Message!! (한글 테스트 💻)");
    }
}
