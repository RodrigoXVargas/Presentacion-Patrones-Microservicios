package com.gayatech.product_service.services;

import com.gayatech.product_service.dtos.ProductDTO;
import com.gayatech.product_service.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductService {

    public List<ProductDTO> getAll();
    public ProductDTO getOneProduct(Long id);
    public ProductDTO createProduct(ProductDTO productDTO);
    public ProductDTO updateProduct(Long idUpdate, ProductDTO productDTO);
    public void deleteProduct(Long id);
    public List<ProductDTO> getListProductByListID(List<Long> longList);

}
