package com.Fashioncart.service;

import com.Fashioncart.dto.ProductDTO;
import com.Fashioncart.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO dto);
    Product updateProduct(Long id, ProductDTO dto);
    void deleteProduct(Long id);
    Product getProduct(Long id);
    List<Product> getAllProducts();
}

