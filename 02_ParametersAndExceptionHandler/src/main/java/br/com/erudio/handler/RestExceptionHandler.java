package br.com.erudio.handler;

import br.com.erudio.exceptions.ExceptionResponse;
import br.com.erudio.exceptions.UnsupportedMathOperationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class RestExceptionHandler{

    //Exceção especifica
    @ExceptionHandler(UnsupportedMathOperationException.class)
    public final ResponseEntity<ExceptionResponse> handlerUnsupportedMathOperationExceptions(
            UnsupportedMathOperationException e, WebRequest request) {
        ExceptionResponse ex = new ExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false));

        return ResponseEntity.badRequest().body(ex);
    }

    //Exceção mais genérica
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerGenericExceptions(
        Exception e, WebRequest request) {
        ExceptionResponse ex = new ExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false));

        return ResponseEntity.internalServerError().body(ex);
    }
}
