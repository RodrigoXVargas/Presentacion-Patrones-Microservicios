package com.gayatech.product_service;

import com.gayatech.product_service.dtos.ProductDTO;
import com.gayatech.product_service.exceptions.CustomException;
import com.gayatech.product_service.modelmappers.ProductMapper;
import com.gayatech.product_service.models.Product;
import com.gayatech.product_service.repositories.IProductRepository;
import com.gayatech.product_service.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private IProductRepository productRepository;

	@Mock
	private ProductMapper productMapper;

	@InjectMocks
	private ProductService productService;

	private Product sampleProduct;
	private ProductDTO sampleProductDTO;

	@BeforeEach
	void setUp() {
		sampleProduct = new Product();
		sampleProduct.setId(1L);
		sampleProduct.setName("Test Product");
		sampleProduct.setPrice(100.0);
		sampleProduct.setCode("abcd");
		sampleProduct.setBrand("brand");

		sampleProductDTO = new ProductDTO();
		sampleProductDTO.setName("Test Product");
		sampleProductDTO.setPrice(100.0);
		sampleProductDTO.setCode("abcd");
		sampleProductDTO.setBrand("brand");
	}

	@Test
	void getAll_ShouldReturnListOfProducts() {
		// Arrange
		when(productRepository.findAll()).thenReturn(Arrays.asList(sampleProduct));
		when(productMapper.convertToDTOList(anyList())).thenReturn(Arrays.asList(sampleProductDTO));

		// Act
		List<ProductDTO> result = productService.getAll();

		// Assert
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("Test Product", result.get(0).getName());
	}

	@Test
	void getAll_ShouldThrowNoContent_WhenNoProducts() {
		when(productRepository.findAll()).thenReturn(Collections.emptyList());
		List<ProductDTO> result = productService.getAll();
		assertTrue(result.isEmpty(), "La lista debería estar vacía");
	}

	
	@Test
	void getOneProduct_ShouldReturnProduct_WhenIdExists() {
		when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));
		when(productMapper.convertToDTO(sampleProduct)).thenReturn(sampleProductDTO);

		ProductDTO result = productService.getOneProduct(1L);

		assertNotNull(result);
		assertEquals("Test Product", result.getName());
	}

	@Test
	void getOneProduct_ShouldThrowNotFound_WhenIdDoesNotExist() {
		when(productRepository.findById(1L)).thenReturn(Optional.empty());

		CustomException exception = assertThrows(CustomException.class, () -> productService.getOneProduct(1L));

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
		assertEquals("Product ID not found", exception.getMessage());
	}

	@Test
	void createProduct_ShouldSaveAndReturnProductDTO() {
		when(productMapper.convertToModel(sampleProductDTO)).thenReturn(sampleProduct);
		when(productRepository.save(sampleProduct)).thenReturn(sampleProduct);
		when(productMapper.convertToDTO(sampleProduct)).thenReturn(sampleProductDTO);

		ProductDTO result = productService.createProduct(sampleProductDTO);

		assertNotNull(result);
		assertEquals("Test Product", result.getName());
	}

	@Test
	void updateProduct_ShouldUpdateAndReturnProductDTO() {
		when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));
		when(productRepository.save(Mockito.<Product>any())).thenReturn(sampleProduct);
		when(productMapper.convertToDTO(sampleProduct)).thenReturn(sampleProductDTO);

		ProductDTO result = productService.updateProduct(1L, sampleProductDTO);

		assertNotNull(result);
		assertEquals("Test Product", result.getName());
	}

	@Test
	void updateProduct_ShouldThrowNotFound_WhenIdDoesNotExist() {
		when(productRepository.findById(1L)).thenReturn(Optional.empty());

		CustomException exception = assertThrows(CustomException.class, () -> productService.updateProduct(1L, sampleProductDTO));

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
		assertEquals("Product ID not found", exception.getMessage());
	}

	@Test
	void deleteProduct_ShouldDelete_WhenIdExists() {
		when(productRepository.existsById(1L)).thenReturn(true);
		doNothing().when(productRepository).deleteById(1L);

		assertDoesNotThrow(() -> productService.deleteProduct(1L));
		verify(productRepository, times(1)).deleteById(1L);
	}



	@Test
	void deleteProduct_ShouldThrowNotFound_WhenIdDoesNotExist() {
		when(productRepository.existsById(1L)).thenReturn(false);

		CustomException exception = assertThrows(CustomException.class, () -> productService.deleteProduct(1L));

		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
		assertEquals("Product ID not found", exception.getMessage());
	}
}
