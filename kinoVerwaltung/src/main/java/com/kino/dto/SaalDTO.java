package com.kino.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO für Saal – enthält nur die Felder, die der Client benötigt.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaalDTO {
    private Long id;
    private String name;
    private int anzahlReihen;
    private boolean istFreigegeben;
}