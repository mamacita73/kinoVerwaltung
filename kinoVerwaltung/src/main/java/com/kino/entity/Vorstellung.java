package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

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

    @Column(name = "dauer_minuten", nullable = false)
    private int dauerMinuten;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "saal_id", nullable = false)
    @JsonBackReference
    private Saal saal;
}
