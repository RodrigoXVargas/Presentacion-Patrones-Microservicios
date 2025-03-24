package com.gayatech.cart_service.dtos.DTOsResponse;

import com.gayatech.cart_service.dtos.DTOsModels.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

    private Long id;
    private List<ProductDTO> productDTOList;

}
