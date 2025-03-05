package com.kino.dto;

import java.util.List;

public class MultiVorstellungenDTO {
    private String filmTitel;
    private List<Long> saalIds;
    private List<String> startzeiten;
    private int dauerMinuten;

    // Standard-Konstruktor
    public MultiVorstellungenDTO() {
    }

    // Konstruktor mit allen Parametern
    public MultiVorstellungenDTO(String filmTitel, List<Long> saalIds, List<String> startzeiten, int dauerMinuten) {
        this.filmTitel = filmTitel;
        this.saalIds = saalIds;
        this.startzeiten = startzeiten;
        this.dauerMinuten = dauerMinuten;
    }

    // Getter und Setter

    public String getFilmTitel() {
        return filmTitel;
    }

    public void setFilmTitel(String filmTitel) {
        this.filmTitel = filmTitel;
    }

    public List<Long> getSaalIds() {
        return saalIds;
    }

    public void setSaalIds(List<Long> saalIds) {
        this.saalIds = saalIds;
    }

    public List<String> getStartzeiten() {
        return startzeiten;
    }

    public void setStartzeiten(List<String> startzeiten) {
        this.startzeiten = startzeiten;
    }

    public int getDauerMinuten() {
        return dauerMinuten;
    }

    public void setDauerMinuten(int dauerMinuten) {
        this.dauerMinuten = dauerMinuten;
    }
}
