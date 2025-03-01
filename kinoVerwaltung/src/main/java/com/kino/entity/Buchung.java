package com.kino.entity;
import jakarta.persistence.*;
import com.kino.entity.Sitz;
import lombok.*;

import java.util.Date;
import java.util.List;


// Buchung-Entity: Repräsentiert eine Buchung eines Sitzplatzes durch einen Benutzer
@Entity
@Table(name = "buchung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Buchung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Eindeutige Buchungsnummer
    @Column(name = "buchungsnummer")
    private String buchungsnummer;

    // Datum und Uhrzeit der Buchung
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datum")
    private Date datum;

    // Preis der Buchung
    @Column(name = "preis")
    private double preis;

    @ManyToOne
    @JoinColumn(name = "benutzer_id")
    private Benutzer benutzer;

    @Column(name = "status")
    private String status;  // z. B. "OFFEN", "STORNIERT", "GEBUCHT"

    // Eine Buchung kann mehrere Sitzplätze umfassen (Many-to-Many)
    @ManyToMany
    @JoinTable(name = "buchung_sitzplatz",
            joinColumns = @JoinColumn(name = "buchung_id"),
            inverseJoinColumns = @JoinColumn(name = "sitzplatz_id")
    )
    private List<Sitz> sitzplaetze;

    // Berechnet den Preis; Beispielimplementierung (anpassen nach Bedarf)
    public double berechnePreis() {
        // Hier könnte man basierend auf den Sitzkategorien den Preis berechnen.
        return preis;
    }

    // Gibt formatierte Buchungsdetails zurück
    public String getBuchungsdetails() {
        StringBuilder details = new StringBuilder();
        details.append("Buchungsnummer: ").append(buchungsnummer)
                .append("\nDatum: ").append(datum)
                .append("\nPreis: ").append(preis)
                .append("\nSitzplätze: ");
        if (sitzplaetze != null) {
            for (Sitz sp : sitzplaetze) {
                details.append(sp.getNummer()).append(" ");
            }
        }
        return details.toString();
    }
}