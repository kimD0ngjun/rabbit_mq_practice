package com.example.rabbit_mq.controller;

import com.example.rabbit_mq.dto.UserDto;
import com.example.rabbit_mq.publisher.RabbitMQJsonProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MessageJsonController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    // http://localhost:8080/api/v1/publish
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserDto user) {
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("JSON Message send to RabbitMQ ...");
    }
}
