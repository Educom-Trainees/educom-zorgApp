package nu.zapp.ExceptionHandler;

public class DatabaseFailedToConnect extends RuntimeException {
    public DatabaseFailedToConnect() {
        super("database is momenteel niet beschikbaar");
    }
}
