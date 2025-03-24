package com.gayatech.product_service.controllers;

import com.gayatech.product_service.dtos.CartRequestDTO;
import com.gayatech.product_service.dtos.ProductDTO;
import com.gayatech.product_service.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(productService.getOneProduct(id));
    }

    @PostMapping("/list")
    public ResponseEntity<List<ProductDTO>> getListByListId(@RequestBody CartRequestDTO cartRequestDTO){
        return ResponseEntity.ok(productService.getListProductByListID(cartRequestDTO.getIdProductList()));
    }

    @PostMapping("/")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO newProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> editProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
