package com.example.springboot3jwtMyproject.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@ResponseStatus
public class RestResponseEntityException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseEntity> ResourceNotFound(ResourceNotFoundException exception){
        ErrorResponseEntity message = new ErrorResponseEntity(exception.getMessage(),true);
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message)
        return  new ResponseEntity<ErrorResponseEntity>(message, HttpStatus.PRECONDITION_FAILED);
    }
}
