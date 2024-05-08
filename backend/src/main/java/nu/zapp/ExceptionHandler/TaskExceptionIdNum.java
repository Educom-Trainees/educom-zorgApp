package nu.zapp.ExceptionHandler;

public class TaskExceptionIdNum extends RuntimeException {
    public TaskExceptionIdNum(int id) {
        super("Er is geen taak met id " + id);
    }
}
