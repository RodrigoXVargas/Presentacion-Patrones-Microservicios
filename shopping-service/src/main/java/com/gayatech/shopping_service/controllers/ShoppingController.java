package com.gayatech.shopping_service.controllers;

import com.gayatech.shopping_service.dtos.ShoppingDTO;
import com.gayatech.shopping_service.services.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {


    @Autowired
    private IShoppingService shoppingService;

    @GetMapping("/")
    public ResponseEntity<List<ShoppingDTO>> getAll(){
        return ResponseEntity.ok(shoppingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(shoppingService.getOne(id));
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<ShoppingDTO>> getListByListId(@RequestBody CartRequestDTO cartRequestDTO){
//        return ResponseEntity.ok(productService.getListProductByListID(cartRequestDTO.getIdProductList()));
//    }

    @PostMapping("/")
    public ResponseEntity<ShoppingDTO> createProduct(@RequestBody ShoppingDTO productDTO){
        ShoppingDTO newProduct = shoppingService.create(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingDTO> editProduct(@PathVariable Long id, @RequestBody ShoppingDTO productDTO){
        return ResponseEntity.ok(shoppingService.update(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        shoppingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
