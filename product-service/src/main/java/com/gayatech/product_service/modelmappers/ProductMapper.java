package com.gayatech.product_service.modelmappers;

import com.gayatech.product_service.dtos.ProductDTO;
import com.gayatech.product_service.models.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMapper {

    private ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<ProductDTO> convertToDTOList(List<Product> productList) {
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {}.getType());
    }

    public List<Product> convertToModelList(List<ProductDTO> productDTOList) {
        return modelMapper.map(productDTOList, new TypeToken<List<Product>>() {}.getType());
    }

    public ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product convertToModel(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
