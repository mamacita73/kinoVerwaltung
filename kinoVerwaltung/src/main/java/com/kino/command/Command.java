package com.kino.command;

public interface Command<R> {
    /**
     * Führt die Operation aus und speichert das Ergebnis oder eine Exception.
     */
    void execute();

    /**
     * Gibt das Ergebnis der Operation zurück oder wirft eine Exception, falls die
     * @return
     * @throws Exception
     */
    R getResult() throws Exception;
}