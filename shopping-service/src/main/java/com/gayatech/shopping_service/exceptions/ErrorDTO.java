package com.gayatech.shopping_service.exceptions;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private String message;
}
