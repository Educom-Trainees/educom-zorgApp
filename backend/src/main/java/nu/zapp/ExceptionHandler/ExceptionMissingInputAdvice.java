package nu.zapp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionMissingInputAdvice {
    @ResponseBody
    @ExceptionHandler(ExceptionMissingInput.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String ExceptionMissingInput(ExceptionMissingInput ex) {
        return ex.getMessage();
    }
}
