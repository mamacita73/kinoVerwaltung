package com.kino.service;

import com.kino.entity.Benutzer;
import com.kino.entity.Rolle;
import com.kino.repository.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    /**
     * Prüft, ob ein Benutzer mit der angegebenen E-Mail existiert.
     *
     * @param email Die E-Mail-Adresse des Benutzers.
     * @return Optional&lt;Benutzer&gt; – leer, wenn kein Benutzer gefunden wird.
     */
    public Optional<Benutzer> login(String email) {
        return benutzerRepository.findByEmail(email);
    }

    /**
     * Registriert einen neuen Benutzer. (Dieser Service wird zwar verwendet,
     * der eigentliche Persistierungsvorgang erfolgt später asynchron via RabbitMQ.)
     *
     * @param benutzer Das zu registrierende Benutzerobjekt.
     * @return Der gespeicherte Benutzer.
     */
    public Benutzer register(Benutzer benutzer) {
        // Hier könnte auch Logik ergänzt werden (z. B. Hashing des Passworts)
        return benutzerRepository.save(benutzer);
    }
}
