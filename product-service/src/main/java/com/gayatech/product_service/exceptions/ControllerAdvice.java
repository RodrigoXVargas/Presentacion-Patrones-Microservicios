package com.gayatech.product_service.exceptions;

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

}
