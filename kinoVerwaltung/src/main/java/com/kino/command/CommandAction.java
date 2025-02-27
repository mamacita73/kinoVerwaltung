package com.kino.command;

@FunctionalInterface
public interface CommandAction<R> {
    /**
     * Führt die übergebene Logik aus und gibt ein Ergebnis zurück.
     */
    R apply() throws Exception;
}