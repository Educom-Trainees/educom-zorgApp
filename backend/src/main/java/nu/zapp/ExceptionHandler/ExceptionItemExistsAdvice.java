package nu.zapp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class ExceptionItemExistsAdvice {
    @ResponseBody
    @ExceptionHandler(ExceptionItemExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String ExceptionItemExists(ExceptionItemExists ex) {
        return ex.getMessage();
    }
}
