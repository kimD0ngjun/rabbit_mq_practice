package com.example.rabbit_mq.publisher;

import com.example.rabbit_mq.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.jsonKey}")
    private String routingJsonKey;

    // create a logger object named LOGGER for use in the RabbitMQJsonProducer class
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(UserDto user) {
        LOGGER.info("Sending JSON message: {}", user.toString());
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }
}
