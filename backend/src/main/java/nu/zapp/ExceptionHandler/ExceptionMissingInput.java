package nu.zapp.ExceptionHandler;

public class ExceptionMissingInput extends RuntimeException {

    public ExceptionMissingInput(String type){
        super("De " + type + " ontbreekt");
    }
}

