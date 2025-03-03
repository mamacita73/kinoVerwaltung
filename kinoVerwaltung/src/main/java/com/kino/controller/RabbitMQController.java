package com.kino.controller;

import org.springframework.web.bind.annotation.*;

import com.kino.messaging.RabbitMQSender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * REST-Schnittstelle, um Nachrichten an RabbitMQ zu senden
 */

@RestController
@RequestMapping("/rabbitmq")
@CrossOrigin
public class RabbitMQController {
    private final RabbitMQSender sender;

    public RabbitMQController(RabbitMQSender sender) {
        this.sender = sender;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) throws IOException, TimeoutException {
        sender.sendLoginRequest(message);
        return "Gesendet: " + message;
    }
}
