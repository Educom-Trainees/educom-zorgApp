## Exception handling
Most exceptions can be handled internally. These exceptions are to provide a clearer indication of the issue - 
particularly when using a tool such as Postman.

### Exception and advice
All exceptions have two files. 
The RuntimeException extension where the error message is set and the advice file.

```@ControllerAdvice``` lets Springboot identify the advice class and use it for a response body, 
which is shown when the error is hit.

### TODOs