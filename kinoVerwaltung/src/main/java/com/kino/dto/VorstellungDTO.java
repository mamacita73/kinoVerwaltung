package com.kino.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Einfaches DTO für Vorstellung
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VorstellungDTO {
    private Long id;
    private Long saalId;
    private String filmTitel;
    private String startzeit;
    private int dauerMinuten;
}
