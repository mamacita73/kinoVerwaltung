package com.kino.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/**
 * fürs Empfangen von Nachrichten
 */

@Service
public class RabbitMQReceiver {
    @RabbitListener(queues = "kinoQueue")
    public void receiveMessage(String message) {
        System.out.println("Empfangen: " + message);
    }
}
