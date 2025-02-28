package com.kino.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        // Hautomatisch, wenn backend staret
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        // RabbitTemplate für Sync/Async Calls
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue loginQueue() {
        // Deklariert eine Queue mit dem Namen "loginQueue"
        // false = non-durable, je nach Bedarf anpassen
        return new Queue("loginQueue", false);
    }


    @Bean
    public Queue asyncCommandQueue() {
        //  wird vom Backend verwendet und muss existieren
        return new Queue("asyncCommandQueue", true);
    }

    @Bean
    public Queue registerQueue() {
        return new Queue("registerQueue", true); // Persistente Queue für Registrierung
    }

}
