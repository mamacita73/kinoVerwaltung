package com.kino.controller;

import com.kino.entity.Saal;
import com.kino.messaging.AsyncCommandSender;
import com.kino.service.SaalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/saal")
public class SaalController {

    @Autowired
    private SaalService saalService;

    @GetMapping
    public List<Saal> getAllSäle() {
        return saalService.getAllSäle();
    }

    @GetMapping("/{id}")
    public Optional<Saal> getSaalById(@PathVariable Long id) {
        return saalService.getSaalById(id);
    }

    /**
     * Nimmt den JSON-Payload entgegen und sendet ihn als "SAAL_QUERY" an RabbitMQ.
     */
    @PostMapping("/anlegen")
    public ResponseEntity<?> anlegen(@RequestBody Map<String, Object> body) {
        try {
            // Sendet den gesamten Body an RabbitMQ
            AsyncCommandSender.sendCommand("SAAL_QUERY", body);
            return ResponseEntity.ok("Saal-Erstellen Command an RabbitMQ");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fehler beim Senden an RabbitMQ: " + e.getMessage());
        }
    }
}
