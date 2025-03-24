package com.gayatech.product_service.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
