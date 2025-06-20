package com.Fashioncart.service;

import com.Fashioncart.dto.ProductDTO;
import com.Fashioncart.model.Product;
import com.Fashioncart.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testCreateProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setName("Shirt");
        dto.setDescription("Cotton");
        dto.setPrice(299.99);

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName(dto.getName());
        savedProduct.setDescription(dto.getDescription());
        savedProduct.setPrice(dto.getPrice());

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.createProduct(dto);

        assertNotNull(result);
        assertEquals("Shirt", result.getName());
        assertEquals(299.99, result.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testGetProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);

        assertNotNull(result);
        assertEquals("Test", result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    void testUpdateProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setName("Updated");
        dto.setDescription("Updated desc");
        dto.setPrice(500);

        Product existing = new Product();
        existing.setId(1L);
        existing.setName("Old");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenReturn(existing);

        Product result = productService.updateProduct(1L, dto);

        assertEquals("Updated", result.getName());
        assertEquals(500, result.getPrice());
        verify(productRepository).save(existing);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void testGetAllProducts() {
        Product p1 = new Product();
        Product p2 = new Product();
        when(productRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Product> result = productService.getAllProducts();
        assertEquals(2, result.size());
    }
}

