package com.gayatech.shopping_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorDTO> customExcepcionHandler(CustomException e){
        ErrorDTO errorDTO = ErrorDTO.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(errorDTO, e.getStatus());
    }

    @ExceptionHandler(value = PartialContentException.class)
    public ResponseEntity<?> partialContentExcepcionHandler(PartialContentException e){
        return new ResponseEntity<>(e.getObject(), HttpStatus.PARTIAL_CONTENT);
    }
}
