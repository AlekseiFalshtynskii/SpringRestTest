package ru.test.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.test.hotel.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = new ArrayList<>(ex.getBindingResult().getFieldErrorCount());
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.add(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return errors;
    }
}
