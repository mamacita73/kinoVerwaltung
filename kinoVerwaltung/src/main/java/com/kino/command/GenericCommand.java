package com.kino.command;

public class GenericCommand<R> implements Command<R> {

    private final CommandAction<R> action;
    private R result;
    private Exception exception;

    public GenericCommand(CommandAction<R> action) {
        this.action = action;
    }

    @Override
    public void execute() {
        try {
            result = action.apply();
        } catch (Exception e) {
            exception = e;
        }
    }

    @Override
    public R getResult() throws Exception {
        if (exception != null) {
            throw exception;
        }
        return result;
    }
}
