package com.gayatech.cart_service.dtos.DTOsModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String code;
    private String name;
    private String brand;
    private Double price;

}
