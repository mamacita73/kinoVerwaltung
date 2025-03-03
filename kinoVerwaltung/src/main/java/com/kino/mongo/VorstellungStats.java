package com.kino.mongo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vorstellung_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VorstellungStats {

    @Id
    private Long vorstellungId;

    private String filmTitel;

    private int totalRevenue; // z. B. Gesamteinnahmen
}