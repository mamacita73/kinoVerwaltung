package com.kino.entity;
import jakarta.persistence.*;
import com.kino.entity.Sitzkategorie;
import com.kino.entity.Sitzreihe;
import com.kino.entity.Sitzstatus;
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
    private Sitzreihe sitzreihe;
    public void setSitzreihe(Sitzreihe sitzreihe) {
        this.sitzreihe = sitzreihe;
    }
}
