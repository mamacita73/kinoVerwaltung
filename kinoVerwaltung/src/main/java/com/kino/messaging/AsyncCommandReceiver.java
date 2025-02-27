package com.kino.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.command.Command;
import com.kino.command.CommandFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class AsyncCommandReceiver {
    private final ObjectMapper objectMapper;
    private final CommandFactory commandFactory;

    public AsyncCommandReceiver(ObjectMapper objectMapper, CommandFactory commandFactory) {
        this.objectMapper = objectMapper;
        this.commandFactory = commandFactory;
    }

    /**
     * Wird ausgeführt, wenn eine Nachricht auf der Queue "asyncCommandQueue" ankommt.
     * @param message
     */
    @RabbitListener(queues = "asyncCommandQueue")
    public void handleAsyncCommand(String message) {
        try {
            // Deserialisiere die Nachricht in eine Map
            Map<String, Object> msgMap = objectMapper.readValue(message, Map.class);
            String commandType = (String) msgMap.get("command");
            Map<String, Object> payload = (Map<String, Object>) msgMap.get("payload");

            // Erzeuge das passende Command und führe es aus
            Command<?> cmd = commandFactory.createCommand(commandType, payload);
            cmd.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
