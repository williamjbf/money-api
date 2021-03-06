package com.github.williamjbf.moneyapi.exceptionhandler;

import com.github.williamjbf.moneyapi.service.exception.PersonNotExistOrInactiveException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MoneyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        String userMessage = messageSource.getMessage(
                "invalid.message",
                null,
                LocaleContextHolder.getLocale());
        String devMessage =ex.getCause() != null ? ex.getCause().toString() : ex.toString();

        List<Erro> erros = List.of(new Erro(userMessage, devMessage));
        return handleExceptionInternal(ex,erros,headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<Erro> erros = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, erros,headers, status, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){

        String userMessage = messageSource.getMessage(
                "resource.not-found",
                null,
                LocaleContextHolder.getLocale());

        String devMessage = ex.toString();

        List<Erro> erros = List.of(new Erro(userMessage, devMessage));

        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){

        String userMessage = messageSource.getMessage(
                "resource.operation-not-allowed",
                null,
                LocaleContextHolder.getLocale());

        String devMessage = ExceptionUtils.getRootCauseMessage(ex);

        List<Erro> erros = List.of(new Erro(userMessage, devMessage));

        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler({PersonNotExistOrInactiveException.class})
    public ResponseEntity<Object> handlePersonNotExistOrInactiveException(PersonNotExistOrInactiveException ex, WebRequest request){
        String userMessage = messageSource.getMessage(
                "person.not-exist-or-inactive",
                null,
                LocaleContextHolder.getLocale());

        String devMessage = ExceptionUtils.getRootCauseMessage(ex);

        List<Erro> erros = List.of(new Erro(userMessage, devMessage));
        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

    private List<Erro> createErrorList(BindingResult bindingResult){
        List<Erro> erroList = new ArrayList<>();

        for (FieldError fieldError: bindingResult.getFieldErrors()){
            String userMessage = messageSource.getMessage(fieldError,LocaleContextHolder.getLocale());
            String devMessage = fieldError.toString();

            erroList.add(new Erro(userMessage,devMessage));
        }

        return erroList;
    }

    public static class Erro{

        private String userMessage;
        private String developerMessage;

        public Erro(String userMessage, String devMessage) {
            this.userMessage = userMessage;
            this.developerMessage = devMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public void setUserMessage(String userMessage) {
            this.userMessage = userMessage;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

        public void setDeveloperMessage(String devMessage) {
            this.developerMessage = devMessage;
        }
    }

}
