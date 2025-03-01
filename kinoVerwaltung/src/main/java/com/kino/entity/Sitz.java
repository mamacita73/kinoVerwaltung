package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // Sitz-Nummer innerhalb der Reihe (z.B. 1,2,3...)
    @Column(nullable = false)
    private int nummer;

    // Kategorie (PARKETT, LOGE, LOGE_SERVICE)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sitzkategorie kategorie;

    // Status (FREI, RESERVIERT, BLOCKIERT,)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sitzstatus status = Sitzstatus.FREI;

    @ManyToOne
    @JoinColumn(name = "sitzreihe_id", nullable = false)
    @JsonBackReference
    private Sitzreihe sitzreihe;
    public void setSitzreihe(Sitzreihe sitzreihe) {
        this.sitzreihe = sitzreihe;
    }
    // Prüft, ob der Sitz frei ist (Sitzstatus = FREI)
    public boolean istVerfuegbar() {
        return this.status == Sitzstatus.FREI;
    }

    // Setzt den Sitzstatus auf RESERVIERT
    public void reservieren() {
        setStatus(Sitzstatus.RESERVIERT);
    }

    // Setzt den Sitzstatus auf GEBUCHT
    public void buchen() {
        setStatus(Sitzstatus.GEBUCHT);
    }
}
