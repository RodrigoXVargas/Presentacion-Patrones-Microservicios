package com.gayatech.cart_service.repositories;

import com.gayatech.cart_service.dtos.DTOsModels.ProductDTO;
import com.gayatech.cart_service.dtos.DTOsRequest.CartRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("product-service")
public interface IProductRepository {

    @GetMapping("/api/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id);

    @PostMapping("/api/products/list")
    public List<ProductDTO> getListProductsByListId(@RequestBody CartRequestDTO cartRequestDTO);

}
