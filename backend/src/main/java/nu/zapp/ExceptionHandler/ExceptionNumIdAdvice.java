package nu.zapp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class ExceptionNumIdAdvice {
    @ResponseBody
    @ExceptionHandler(ExceptionNumId.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ExceptionNumId(ExceptionNumId ex) {
        return ex.getMessage();
    }
}
