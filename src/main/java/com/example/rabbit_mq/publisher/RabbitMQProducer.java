package com.example.rabbit_mq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // create a logger object named LOGGER for use in the RabbitMQProducer class
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // logic to send a message to the rabbitMQ broker
    public void sendMessage(String message) {
        LOGGER.info("Sending message: {}", message);
        // to send exchange with routing key for select queue destination
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
