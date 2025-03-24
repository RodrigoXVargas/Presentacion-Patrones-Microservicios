package com.gayatech.cart_service.services;

import com.gayatech.cart_service.dtos.DTOsModels.ProductDTO;
import com.gayatech.cart_service.dtos.DTOsRequest.CartRequestDTO;
import com.gayatech.cart_service.dtos.DTOsResponse.CartResponseDTO;
import com.gayatech.cart_service.exceptions.CustomException;
import com.gayatech.cart_service.exceptions.PartialContentException;
import com.gayatech.cart_service.models.Cart;
import com.gayatech.cart_service.repositories.ICartRepository;
import com.gayatech.cart_service.repositories.IProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository repoCart;

    @Autowired
    private IProductRepository repoProduct;


    @Override
    public List<CartResponseDTO> getAll() {
        //Obtenemos los Carts
        List<Cart> cartList = repoCart.findAll();

        return getListCartResponseDTO(cartList);
    }

    @Override
    public CartResponseDTO getOneCart(Long id) {
        //Buscamos el Cart y si no lo encuentra lanza exception custom manejada por controller advice
        Cart cart = repoCart.findById(id)
                .orElseThrow(() -> new CustomException("Cart not found", HttpStatus.NOT_FOUND));

        return getProductsCartResponseDTO(cart);
    }

    @Override
    @Transactional
    public CartResponseDTO createCart(CartRequestDTO cartRequestDTO) {
        //convertimos el DTO en Model y lo mandamos a guardar
        Cart cart = repoCart.save(new Cart(null, cartRequestDTO.getIdProductList()));

        return getProductsCartResponseDTO(cart);
    }

    @Override
    @Transactional
    public CartResponseDTO updateCart(Long idUpdate, CartRequestDTO cartRequestDTO) {
        //Buscamos el Cart y si no lo encuentra lanza exception custom manejada por controller advice
        Cart cart = repoCart.findById(idUpdate)
                .orElseThrow(() -> new CustomException("Cart not found", HttpStatus.NOT_FOUND));


        //actualizamos la lista de longs
        cart.setIdProductList(cartRequestDTO.getIdProductList());

        //Guardamos el objeto actualizado
        Cart cartSaved = repoCart.save(cart);

        return getProductsCartResponseDTO(cartSaved);
    }

    @Override
    @Transactional
    public void deleteCart(Long id) {
        if (!repoCart.existsById(id)) throw new CustomException("Cart not found", HttpStatus.NOT_FOUND);
        repoCart.deleteById(id);
    }

    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackResponseListCart")
    @Retry(name = "product-service")
    public List<CartResponseDTO> getListCartResponseDTO(List<Cart> cartList){
        List<CartResponseDTO> cartResponseDTOSList = new ArrayList<>();
        AtomicBoolean bandera = new AtomicBoolean(false);

        cartList.forEach(cart -> {
            List<ProductDTO> productDTOList;
            CartResponseDTO cartDTO = new CartResponseDTO();
            try{
                productDTOList = repoProduct.getListProductsByListId(new CartRequestDTO(cart.getIdProductList()));
                cartDTO.setId(cart.getId());
                cartDTO.setProductDTOList(productDTOList);
            } catch (Exception e){
                bandera.set(true);
                cartDTO = getCartResponseDTO(cart);
            } finally {
                cartResponseDTOSList.add(cartDTO);
            }
        });

        if(bandera.get()) throw new PartialContentException(cartResponseDTOSList);
        //retornar el list de CartDTOs

        return cartResponseDTOSList;

    }

    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackResponseCart")
    @Retry(name = "product-service")
    public CartResponseDTO getProductsCartResponseDTO(Cart cart){
        List<ProductDTO> productDTOList = repoProduct.getListProductsByListId(new CartRequestDTO(cart.getIdProductList()));
        //definimos el CartResponseDTO con el id del Cart guardado y la lista de productos
        return new CartResponseDTO(cart.getId(), productDTOList);
    }

    public CartResponseDTO getCartResponseDTO(Cart cart){
        List<ProductDTO> productDTOList = new ArrayList<>();
        cart.getIdProductList().forEach(id -> {
            productDTOList.add(new ProductDTO(id, null, null, null, null));
        });
        return new CartResponseDTO(cart.getId(), productDTOList);
    }

    public List<CartResponseDTO> fallbackResponseListCart(List<Cart> cartList, Throwable e){
        List<CartResponseDTO> cartResponseDTOList = new ArrayList<>();
        cartList.forEach(cart -> {
            CartResponseDTO cartResponseDTO = getCartResponseDTO(cart);
            cartResponseDTOList.add(cartResponseDTO);
        });

        throw new PartialContentException(cartResponseDTOList);
        //return cartResponseDTOList;
    }

    public CartResponseDTO fallbackResponseCart(Cart cart, Exception e){
        CartResponseDTO cartResponseDTO = getCartResponseDTO(cart);
        throw new PartialContentException(cartResponseDTO);
        //return cartResponseDTO;
    }

}
