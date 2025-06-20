package com.Fashioncart.service;

import com.Fashioncart.dto.ProductDTO;
import com.Fashioncart.model.Product;
import com.Fashioncart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO dto) {
        Product product = repository.findById(id).orElseThrow();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return repository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}

