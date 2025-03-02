package com.kino.command;

public class GenericCommand<R> implements Command<R> {

    private final CommandAction<R> action;
    private R result;
    private Exception exception;

    public GenericCommand(CommandAction<R> action) {
        this.action = action;
    }

    @Override
    public R execute() {
        System.out.println("=== [GenericCommand] execute() aufgerufen ===");
        try {
            result = action.apply();
            System.out.println("=== [GenericCommand] Aktion erfolgreich ausgeführt ===");
        } catch (Exception e) {
            exception = e;
            System.err.println("=== [GenericCommand] Fehler während execute(): " + e.getMessage() + " ===");
            e.printStackTrace();
        }
        return result;  // Hier wird das Ergebnis zurückgegeben
    }

    @Override
    public R getResult() throws Exception {
        if (exception != null) {
            throw exception;
        }
        return result;
    }
}
