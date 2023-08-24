package br.com.erudio.handler;

import br.com.erudio.exceptions.ExceptionResponse;
import br.com.erudio.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
    //Exceção mais genérica
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerGenericExceptions(
                                Exception e, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false));

        return ResponseEntity.internalServerError().body(er);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(
                        ResourceNotFoundException e, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(
                            new Date(),
                            e.getMessage(),
                            request.getDescription(false));
        return ResponseEntity.badRequest().body(er);
    }
}

