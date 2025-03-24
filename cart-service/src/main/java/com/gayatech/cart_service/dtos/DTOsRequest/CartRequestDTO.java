package com.gayatech.cart_service.dtos.DTOsRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {

    private List<Long> idProductList;

}
