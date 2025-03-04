package com.kino.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservierungDTO {
    private Long id;
    private String reservierungsnummer;
    private String datum;
    private String status;
    private String kundenEmail;
    private Long vorstellungId;
    private String filmTitel;
    private String startzeit;

    // Neue Felder:
    private String kategorie;  // z. B. "PARKETT", "LOGE", ...
    private int anzahl;        // Anzahl reservierter Sitze
    private int summe;         // Gesamtpreis
}
