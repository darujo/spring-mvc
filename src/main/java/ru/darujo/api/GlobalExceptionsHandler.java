package ru.darujo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.darujo.exceptions.AppError;
import ru.darujo.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value() ,e.getMessage()),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(BadCredentialsException e){
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value() ,"Не коректный логин или пароль"),HttpStatus.UNAUTHORIZED);
    }
}
