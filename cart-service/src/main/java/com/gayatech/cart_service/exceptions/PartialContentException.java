package com.gayatech.cart_service.exceptions;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartialContentException extends RuntimeException{
    private Object object;
}
