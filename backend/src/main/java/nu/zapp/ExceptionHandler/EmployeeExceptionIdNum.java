package nu.zapp.ExceptionHandler;

public class EmployeeExceptionIdNum extends RuntimeException {
        public EmployeeExceptionIdNum(int id) {
            super("Er is geen werknemer met id " + id);
        }
}

