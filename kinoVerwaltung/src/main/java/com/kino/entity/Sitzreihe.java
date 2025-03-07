package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sitzreihe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sitzreihe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // z.B. "A", "B", "C"
    @Column(nullable = false)
    private String reihenBezeichnung;

    @Column(nullable = false)
    private int anzahlSitze;

    @ManyToOne
    @JoinColumn(name = "saal_id", nullable = false)
    private Saal saal;

    /**
     * Ebenfalls @OrderBy, um multiple bag fetches zu vermeiden,
     * wenn wir gleichzeitig "sitzreihen" und "sitze" fetchen.
     */
    @OneToMany(mappedBy = "sitzreihe", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference
    private List<Sitz> sitze;
}
