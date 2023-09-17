package com.delivery.common.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RabbitMqConfig {
    companion object {
        const val DEFAULT_EXCHANGE_NAME = "delivery.exchange"
        const val DEFAULT_QUEUE_NAME = "delivery.queue"
        const val DEFAULT_ROUTING_KEY_NAME = "delivery.key"
    }

    @Bean
    open fun directExchange(): DirectExchange {
        return DirectExchange(DEFAULT_EXCHANGE_NAME)
    }

    @Bean
    open fun queue(): Queue {
        return Queue(DEFAULT_QUEUE_NAME)
    }

    @Bean
    open fun binding(
        directExchange: DirectExchange,
        queue: Queue,
    ): Binding {
        return BindingBuilder.bind(queue)
            .to(directExchange)
            .with(DEFAULT_ROUTING_KEY_NAME)
    }

    @Bean
    open fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): RabbitTemplate {
        return RabbitTemplate().apply {
            setConnectionFactory(connectionFactory)
            setMessageConverter(messageConverter)
        }
    }

    @Bean
    open fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }
}