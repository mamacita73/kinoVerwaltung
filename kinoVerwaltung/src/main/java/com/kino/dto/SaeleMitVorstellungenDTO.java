package com.kino.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaeleMitVorstellungenDTO {
    private Long saalId;
    private String name;
    private int anzahlReihen;
    private boolean istFreigegeben;
    private List<VorstellungDTO> vorstellungen;
}
