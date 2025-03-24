package com.gayatech.cart_service.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private String message;
}
