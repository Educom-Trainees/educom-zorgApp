package nu.zapp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionInvalidInputAdvice {
    @ResponseBody
    @ExceptionHandler(ExceptionInvalidInput.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ExceptionInvalidInput(ExceptionInvalidInput ex) {
        return ex.getMessage();
    }
}
