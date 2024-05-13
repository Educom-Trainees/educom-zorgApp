package nu.zapp.ExceptionHandler;

public class ExceptionItemExists extends RuntimeException {
    public ExceptionItemExists(String type) {
        super("Deze " + type + " bestaat al in het database");
    }
}
