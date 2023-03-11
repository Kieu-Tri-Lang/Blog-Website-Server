package com.kieutrilang.blogwebsite.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiException handleInvalidArgumentException(MethodArgumentNotValidException exception) {

        Map<String, String> errorMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ApiException.builder()
                .errors(errorMap).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiException handleNotFoundException(NotFoundException exception) {

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("errorMessage", exception.getMessage());

        return ApiException.builder().errors(errorMap).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ExistedException.class)
    public ApiException handleExitedException(ExistedException exception) {

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("errorMessage", exception.getMessage());

        return ApiException.builder().errors(errorMap).build();
    }
}
