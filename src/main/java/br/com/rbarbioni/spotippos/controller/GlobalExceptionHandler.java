package br.com.rbarbioni.spotippos.controller;

import br.com.rbarbioni.spotippos.exception.SpotipposException;
import br.com.rbarbioni.spotippos.model.ErrorResponse;
import br.com.rbarbioni.spotippos.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by renan on 11/02/17.
 */
@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = SpotipposException.class)
    public ErrorResponse handleBaseException(SpotipposException e, HttpServletResponse response){
        logger.error("Error", e);
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getMessage());
        response.setStatus(e.getStatus());
        return errorResponse;
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handleException(Exception e, HttpServletResponse response){
        logger.error("Error", e);
        ErrorResponse errorResponse;

        if(e instanceof ServletException || e instanceof MethodArgumentNotValidException){
            errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }else{
            int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            errorResponse = new ErrorResponse(status, e.getMessage());
        }

        response.setStatus(errorResponse.getStatus());

        return errorResponse;
    }
}