package com.gayatech.shopping_service.services;

import com.gayatech.shopping_service.dtos.ShoppingDTO;

import java.util.List;


public interface IShoppingService {

    public List<ShoppingDTO> getAll();
    public ShoppingDTO getOne(Long id);
    public ShoppingDTO create(ShoppingDTO productDTO);
    public ShoppingDTO update(Long idUpdate, ShoppingDTO productDTO);
    public void delete(Long id);
    public List<ShoppingDTO> getListProductByListID(List<Long> longList);

}
