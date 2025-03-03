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
    @JsonBackReference
    private Kino kino;

    /**
     * Wichtig: @OrderBy("id ASC"), damit wir kein MultipleBagFetchException bekommen,
     * wenn wir Saal und Sitzreihen gemeinsam fetchen.
     */
    @OneToMany(mappedBy = "saal", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    @JsonIgnore
    private List<Sitzreihe> sitzreihen;

    @OneToMany(mappedBy = "saal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Vorstellung> vorstellungen = new ArrayList<>();
}
