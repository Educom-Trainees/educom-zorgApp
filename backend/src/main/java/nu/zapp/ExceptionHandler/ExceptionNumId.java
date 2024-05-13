package nu.zapp.ExceptionHandler;

public class ExceptionNumId extends RuntimeException {
        public ExceptionNumId(int id, String type) {
            super("Er is geen " + type + " met id " + id);
        }
    }

