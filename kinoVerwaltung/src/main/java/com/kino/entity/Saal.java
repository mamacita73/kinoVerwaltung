package com.kino.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Kino kino;

    @OneToMany(mappedBy = "saal", cascade = CascadeType.ALL)
    private List<Sitzreihe> sitzreihen;

}
