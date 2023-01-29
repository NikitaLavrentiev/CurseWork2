package exeptions;

public class IllegalParemetrtException extends Exception {
    private final String argument;

    public IllegalParemetrtException(String argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        return argument + " задан некорректно";
    }
}
