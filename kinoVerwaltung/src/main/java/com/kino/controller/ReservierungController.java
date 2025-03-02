package com.kino.controller;

import com.kino.command.Command;
import com.kino.command.CommandFactory;
import com.kino.dto.ReservierungDTO;
import com.kino.entity.Reservierung;
import com.kino.entity.Vorstellung;
import com.kino.messaging.AsyncCommandSender;
import com.kino.repository.VorstellungRepository;
import com.kino.service.ReservierungService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservierung")
public class ReservierungController {
    @Autowired
    private ReservierungService reservierungService;
    @Autowired
    private VorstellungRepository vorstellungRepository;
    @Autowired
    private CommandFactory commandFactory;


    @GetMapping
    @CrossOrigin
    public List<Vorstellung> getAllVorstellungen() {
        return vorstellungRepository.findAll();
    }

    // Endpoint zum Anlegen einer neuen Reservierung
    @PostMapping("/anlegen")
    @CrossOrigin
    public ResponseEntity<?> createReservierung(@RequestBody Map<String, Object> requestBody) {
        try {
            // Optional: E-Mail aus Header setzen, falls benötigt
            String currentUserEmail = getCurrentUserEmail();
            Map<String, Object> payload = (Map<String, Object>) requestBody.get("payload");
            if (payload == null) {
                payload = new HashMap<>();
            }
            // Überschreibe oder setze kundenEmail im Payload
            payload.put("kundenEmail", currentUserEmail);
            requestBody.put("payload", payload);

            // Sende den Command asynchron über RabbitMQ
            AsyncCommandSender.sendCommand("RESERVIERUNG_WRITE", payload);

            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "Reservierungs-Command gesendet.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(409).body(Collections.singletonMap("message", e.getMessage()));
        }
    }


    // Endpoint zum Stornieren einer Reservierung
    @PutMapping("/{id}/cancel")
    @CrossOrigin
    public ResponseEntity<String> cancelReservierung(@PathVariable Long id) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("reservierungId", id);
            AsyncCommandSender.sendCommand("RESERVIERUNG_CANCEL", payload);
            return ResponseEntity.ok("Reservierungs-Cancellation-Command gesendet.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // Optional: Alle Reservierungen eines Benutzers abrufen
    @GetMapping("/byBenutzer/{benutzerId}")
    @CrossOrigin
    public ResponseEntity<List<ReservierungDTO>> getReservierungenByBenutzer(@PathVariable Long benutzerId) {
        List<Reservierung> list = reservierungService.getReservierungenByBenutzerId(benutzerId);

        List<ReservierungDTO> dtos = list.stream().map(r -> {
            String email = (r.getBenutzer() != null) ? r.getBenutzer().getEmail() : null;
            Long vorId = (r.getVorstellung() != null) ? r.getVorstellung().getId() : null;

            return new ReservierungDTO(
                    r.getId(),
                    r.getReservierungsnummer(),
                    r.getDatum(),
                    r.getStatus(),
                    email,
                    vorId
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    /**
     * Ermittelt die E-Mail des aktuell eingeloggten Benutzers aus dem HTTP-Header.
     */
    private String getCurrentUserEmail() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpServletRequest request = attr.getRequest();
            String email = request.getHeader("X-User-Email");
            if (email != null && !email.isEmpty()) {
                return email;
            }
        }
        throw new RuntimeException("Kein eingeloggter Benutzer gefunden.");
    }

}