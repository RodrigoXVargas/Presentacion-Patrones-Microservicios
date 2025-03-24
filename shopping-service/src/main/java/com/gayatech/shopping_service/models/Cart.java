package com.gayatech.shopping_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {


    private Long id;

    @ElementCollection
    private List<Long> idProductList;

}
