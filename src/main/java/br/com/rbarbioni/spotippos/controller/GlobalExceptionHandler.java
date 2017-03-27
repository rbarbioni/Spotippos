package br.com.rbarbioni.spotippos.controller;

import br.com.rbarbioni.spotippos.exception.SpotipposException;
import br.com.rbarbioni.spotippos.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

/**
 * Created by renan on 11/02/17.
 */
@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = SpotipposException.class)
    public ErrorResponse handleBaseException(SpotipposException e){
        return new ErrorResponse(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handleException(Exception e){
        e.printStackTrace();
        if(e instanceof ServletException){
            return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }else{
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}