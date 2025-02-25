package com.kino.controller;

import org.springframework.web.bind.annotation.*;

import com.kino.messaging.RabbitMQSender;


/**
 * REST-Schnittstelle, um Nachrichten an RabbitMQ zu senden
 */

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {
    private final RabbitMQSender sender;

    public RabbitMQController(RabbitMQSender sender) {
        this.sender = sender;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        sender.sendMessage("kinoQueue", message);
        return "Gesendet: " + message;
    }
}
