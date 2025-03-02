package com.kino.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// Reservierung-Entity: Repräsentiert eine Sitzplatzreservierung
@Entity
@Table(name = "reservierung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservierung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reservierungsnummer;

    @Column(nullable = false)
    private String datum;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "benutzer_id", nullable = false)
    private Benutzer benutzer;

    @ManyToOne
    @JoinColumn(name = "vorstellung_id", nullable = false)
    private Vorstellung vorstellung;

    // Liste der reservierten Sitze
    @OneToMany(mappedBy = "reservierung", cascade = CascadeType.ALL)
    private List<ReservierungSitz> reservierungSitze;
}

