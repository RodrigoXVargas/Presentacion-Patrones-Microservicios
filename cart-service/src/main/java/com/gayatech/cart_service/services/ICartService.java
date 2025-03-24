package com.gayatech.cart_service.services;

import com.gayatech.cart_service.dtos.DTOsRequest.CartRequestDTO;
import com.gayatech.cart_service.dtos.DTOsResponse.CartResponseDTO;

import java.util.List;

public interface ICartService {

    public List<CartResponseDTO> getAll();
    public CartResponseDTO getOneCart(Long id);
    public CartResponseDTO createCart(CartRequestDTO cartRequestDTO);
    public CartResponseDTO updateCart(Long idUpdate, CartRequestDTO cartRequestDTO);
    public void deleteCart(Long id);
}
