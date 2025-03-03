package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sitz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sitz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int nummer;  // Sitznummer in der Reihe

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sitzkategorie kategorie; // PARKETT, LOGE, LOGE_SERVICE

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sitzstatus status = Sitzstatus.FREI; // FREI, RESERVIERT, BLOCKIERT, GEBUCHT etc.

    @ManyToOne
    @JoinColumn(name = "sitzreihe_id", nullable = false)
    @JsonBackReference
    private Sitzreihe sitzreihe;

    // Beispiel-Methoden
    public boolean istVerfuegbar() {
        return this.status == Sitzstatus.FREI;
    }

    public void reservieren() {
        setStatus(Sitzstatus.RESERVIERT);
    }

    public void buchen() {
        setStatus(Sitzstatus.GEBUCHT);
    }
}
