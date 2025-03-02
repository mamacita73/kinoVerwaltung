package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "saal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Saal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "anzahl_reihen", nullable = false)
    private int anzahlReihen;


    @Column(name = "ist_freigegeben", nullable = false)
    private boolean istFreigegeben = false;


    @ManyToOne
    @JoinColumn(name = "kino_id", nullable = true)
    @JsonBackReference  // Rückreferenz, damit Jackson nicht den gesamten Kino-Objektbaum serialisiert
    private Kino kino;

    @OneToMany(mappedBy = "saal", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Sitzreihe> sitzreihen;

    // Bidirektionale Beziehung zu Vorstellung
    @OneToMany(mappedBy = "saal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference  // Verhindert Endlosschleifen beim Serialisieren
    private List<Vorstellung> vorstellungen = new ArrayList<>();

}
