package com.kino.entity;
import jakarta.persistence.*;
import lombok.*;


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

    @Column(nullable = false, unique = true)
    private String buchungsnummer;

    @Column(nullable = false)
    private String datum;

    @Column(nullable = false)
    private double preis;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "benutzer_id", nullable = false)
    private Benutzer benutzer;

    @ManyToOne
    @JoinColumn(name = "vorstellung_id", nullable = false)
    private Vorstellung vorstellung;
}