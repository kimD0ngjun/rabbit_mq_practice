package com.example.rabbit_mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    // create a logger object named LOGGER for use in the RabbitMQConsumer class
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    // read or consume the message from the particular queue
    // select particular queues by queue's name (like @value annotation)
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message) {
        LOGGER.info("Receiving message: {}", message);
    }
}
