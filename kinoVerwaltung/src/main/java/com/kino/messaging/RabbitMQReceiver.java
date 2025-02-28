package com.kino.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.entity.Rolle;
import com.kino.messaging.DatabaseCommand;
import com.kino.entity.Benutzer;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMQReceiver {
    private final DatabaseCommand databaseCommand;
    private final ObjectMapper objectMapper;



    public RabbitMQReceiver(DatabaseCommand databaseCommand, ObjectMapper objectMapper) {
        this.databaseCommand = databaseCommand;
        this.objectMapper = objectMapper;
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Nachricht auf der "loginQueue" ankommt.
     * @param message
     * @return
     */
    @RabbitListener(queues = "loginQueue")
    public String receiveLoginRequest(String message) {
        Map<String, String> response = new HashMap<>();


        try {
            //E-Mail aus dem JSON-Objekt extrahieren
            Map<String, String> loginData = objectMapper.readValue(message, Map.class);
            String email = loginData.get("email");
            System.out.println("Empfangene E-Mail2: " + email);


            //Benutzer in der Datenbank suchen
            Optional<Benutzer> userOpt = databaseCommand.findUserByEmail(email);

            //Rolle ermitteln und Antwort zusammenstellen
            if (userOpt.isPresent()) {
                Benutzer user = userOpt.get();
                response.put("success", "true");
                response.put("rolle", user.getRolle().name());
                System.out.println("Empfangene E-Mail3: " + email);

            } else {
                response.put("success", "false");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\"}";
        }

        //JSON zurückgeben
        try {
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\"}";
        }
    }


    @RabbitListener(queues = "registerQueue")
    public String receiveRegisterRequest(String message) {
        Map<String, String> response = new HashMap<>();

        try {
            // JSON-Daten aus der Nachricht extrahieren
            Map<String, String> registerData = objectMapper.readValue(message, Map.class);
            String benutzername = registerData.get("benutzername");
            String email = registerData.get("email");
            String passwort = registerData.get("passwort"); // Klartext-Passwort bleibt unverändert
            String role = registerData.get("role");

            System.out.println("Neue Registrierung für: " + email);

            // Prüfen, ob der Benutzer bereits existiert
            Optional<Benutzer> userOpt = databaseCommand.findUserByEmail(email);
            if (userOpt.isPresent()) {
                response.put("success", "false");
                response.put("message", "E-Mail bereits registriert.");
                return objectMapper.writeValueAsString(response);
            }

            // Neuen Benutzer mit Klartext-Passwort erstellen
            Benutzer neuerBenutzer = new Benutzer();
            neuerBenutzer.setBenutzername(benutzername);
            neuerBenutzer.setEmail(email);
            neuerBenutzer.setPasswort(passwort);
            neuerBenutzer.setRolle(Rolle.valueOf(role.toUpperCase()));

            databaseCommand.saveUser(neuerBenutzer); // Speichern in der DB

            response.put("success", "true");
            response.put("message", "Registrierung erfolgreich!");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "false");
            response.put("message", "Fehler bei der Registrierung.");
        }

        try {
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\"}";
        }
    }



}


