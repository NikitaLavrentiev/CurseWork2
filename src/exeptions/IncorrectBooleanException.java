package exeptions;

public class IncorrectBooleanException extends Exception {
    private final boolean argument;

    public IncorrectBooleanException(boolean argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        return argument + " задан некорректно";
    }
}

