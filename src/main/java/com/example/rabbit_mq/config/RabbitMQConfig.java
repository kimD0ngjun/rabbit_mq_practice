package com.example.rabbit_mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.jsonQueue.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.jsonKey}")
    private String jsonRoutingKey;

    // spring bean for rabbitmq queue
    // Queue of spring AMQP
    @Bean
    public Queue queue() {
        // queue name
        return new Queue(queue);
    }

    // several queue for storing json message
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange() {
        // exchange name
        return new TopicExchange(exchange);
    }

    // spring bean for rabbitmq binding
    // bind between queue and exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // bind between jsonQueue and exchange using json routing key
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange()) // 동일 exchange 에서 routing key 를 달리해서 다양한 큐에 바인딩
                .with(jsonRoutingKey);
    }

    // 디폴트로 세팅된 RabbitTemplate 가 아닌, JSON Converter 를 갖추게 하기 위한 converter 메소드
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        return rabbitTemplate;
    }

    // ConnectionFactory, RabbitTemplate, RabbitAdmin 등은 이미 auto configuring 됨
}
