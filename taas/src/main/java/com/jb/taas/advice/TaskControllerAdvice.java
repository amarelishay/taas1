package com.jb.taas.advice;

import com.jb.taas.dto.ErrDto;
import com.jb.taas.exceptions.TaskSecurityException;
import com.jb.taas.exceptions.TaskSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kobis on 12 May, 2022
 */
@RestControllerAdvice
public class TaskControllerAdvice {

    @ExceptionHandler(value = {TaskSecurityException.class})
    public ResponseEntity<?> handleSecException(TaskSecurityException e){
        return new ResponseEntity<>(e.getSecMsg().getMsg(),e.getSecMsg().getStatus());
    }

    @ExceptionHandler(value = {TaskSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDto handleException(Exception e){
        return new ErrDto(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
