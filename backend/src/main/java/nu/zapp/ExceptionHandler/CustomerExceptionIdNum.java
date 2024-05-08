package nu.zapp.ExceptionHandler;

public class CustomerExceptionIdNum extends RuntimeException {
    public CustomerExceptionIdNum(int id) {
        super("Er is geen klant met id " + id);
    }
}
