package nu.zapp.ExceptionHandler;

public class ExceptionInvalidInput extends RuntimeException {

    public ExceptionInvalidInput(String type){
        super("Er is iets mis met de " + type);
    }
}
