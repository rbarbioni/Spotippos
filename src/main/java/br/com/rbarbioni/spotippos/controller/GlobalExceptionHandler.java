package br.com.rbarbioni.spotippos.controller;

import br.com.rbarbioni.spotippos.exception.SpotipposException;
import br.com.rbarbioni.spotippos.model.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by renan on 11/02/17.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = SpotipposException.class)
    public ResponseError handleBaseException(SpotipposException e, HttpServletResponse response, HttpServletRequest request){
        logger.error("Error", e);
        return processResponseError(response, request, HttpStatus.valueOf(e.getStatus()), e.getMessage(), null);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseError handleBadRequest(Exception e, HttpServletResponse response, HttpServletRequest request) {
        logger.error("Error", e);
        return processResponseError(response, request, HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseError handleException(Exception e, HttpServletResponse response, HttpServletRequest request){
        logger.error("Error", e);

        ResponseError responseError = processResponseError(response, request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);

        if(e instanceof HttpMessageConversionException){
            responseError = processResponseError(response, request, HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), null);
        }

        response.setStatus(response.getStatus());
        return responseError;
    }

    @ExceptionHandler(value ={MethodArgumentNotValidException.class})
    public ResponseError handleValidationException(Exception e, HttpServletResponse response, HttpServletRequest request){
        logger.error("Error", e);

        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;

        List<String> errorsList = new ArrayList<>(exception.getBindingResult().getErrorCount());

        errorsList.addAll(exception.getBindingResult().getFieldErrors().stream().map(fieldError ->
                getMessage(fieldError.getDefaultMessage()))
                .collect(Collectors.toList()));

        return processResponseError(response, request, HttpStatus.UNPROCESSABLE_ENTITY, getMessage("validation.error"), errorsList);
    }

    private  ResponseError processResponseError (HttpServletResponse response, HttpServletRequest request, HttpStatus httpStatus, String message, List<String> errors){
        response.setStatus(httpStatus.value());
        return new ResponseError(response.getStatus(), httpStatus.name(), message, request.getRequestURI(), errors);
    }

    private String getMessage(String code){
        try{
            return this.messageSource.getMessage(code, null, Locale.getDefault());
        }catch (NoSuchMessageException e){
            return e.getMessage();
        }
    }
}