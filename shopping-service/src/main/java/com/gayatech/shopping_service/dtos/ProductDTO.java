package com.gayatech.shopping_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String code;
    private String name;
    private String brand;
    private Double price;


}
