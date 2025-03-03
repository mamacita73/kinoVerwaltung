package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "umbuchung_datum")
    private LocalDateTime umbuchungDatum;

    @Column(nullable = false, unique = true)
    private String buchungsnummer;

    @Column(name = "kunden_email", nullable = false)
    private String kundenEmail;

    @Column(nullable = false)
    private String status;  // z.B. "GEBUCHT"

    @Column(nullable = false)
    private int summe; // Gesamtsumme der Buchung

    @ManyToOne
    @JoinColumn(name = "benutzer_id", nullable = false)
    @JsonIgnore
    private Benutzer benutzer;

    // Falls die Buchung aus einer Reservierung hervorgeht:
    @OneToOne
    @JoinColumn(name = "reservierung_id")
    private Reservierung reservierung;

    // Direkte Beziehung zur Vorstellung
    @ManyToOne
    @JoinColumn(name = "vorstellung_id", nullable = false)
    private Vorstellung vorstellung;

    @OneToMany(mappedBy = "buchung", cascade = CascadeType.ALL)
    private List<BuchungSitz> buchungSitze = new ArrayList<>();
}
