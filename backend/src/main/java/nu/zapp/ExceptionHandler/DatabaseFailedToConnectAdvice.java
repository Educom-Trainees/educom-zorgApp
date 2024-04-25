package nu.zapp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DatabaseFailedToConnectAdvice {
    @ResponseBody
    @ExceptionHandler(DatabaseFailedToConnect.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    String DatabaseFailedToConnect(DatabaseFailedToConnect ex) {
        return ex.getMessage();
    }
}
