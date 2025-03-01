package com.kino.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


// Vorstellung-Entity: Repräsentiert eine Filmvorstellung
@Entity
@Table(name = "vorstellung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vorstellung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "film_titel", nullable = false)
    private String filmTitel;

    // Nur Uhrzeit, kein Datum
    @Column(name = "startzeit", columnDefinition = "TIME", nullable = false)
    private LocalTime startzeit;

    // UML: dauerMinuten
    @Column(name = "dauer_minuten", nullable = false)
    private int dauerMinuten;

    // Beziehung zum Saal (jeder Vorstellung ist ein Saal zugeordnet)
    @ManyToOne(optional = false)
    @JoinColumn(name = "saal_id", nullable = false)
    private Saal saal;


}
