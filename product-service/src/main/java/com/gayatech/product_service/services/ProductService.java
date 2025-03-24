package com.gayatech.product_service.services;

import com.gayatech.product_service.dtos.ProductDTO;
import com.gayatech.product_service.exceptions.CustomException;
import com.gayatech.product_service.modelmappers.ProductMapper;
import com.gayatech.product_service.models.Product;
import com.gayatech.product_service.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;



    @Override
    public List<ProductDTO> getAll() {
        List<Product> productList = productRepository.findAll();
        return productMapper.convertToDTOList(productList);
    }

    @Override
    public ProductDTO getOneProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product ID not found", HttpStatus.NOT_FOUND));
        return productMapper.convertToDTO(product);
    }

    @Override
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product newProduct = productMapper.convertToModel(productDTO);
        Product productSaved = productRepository.save(newProduct);
        return productMapper.convertToDTO(productSaved);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long idUpdate, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(idUpdate)
                .orElseThrow(() -> new CustomException("Product ID not found", HttpStatus.NOT_FOUND));

        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setBrand(productDTO.getBrand());
        existingProduct.setCode(productDTO.getCode());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.convertToDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new CustomException("Product ID not found", HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getListProductByListID(List<Long> longList) {
        List<Product> productList = productRepository.findAllById(longList);
        return productList.stream()
                .map(product -> productMapper.convertToDTO(product))
                .collect(Collectors.toList());
    }
}
