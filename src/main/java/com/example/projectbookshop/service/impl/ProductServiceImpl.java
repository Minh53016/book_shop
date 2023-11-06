package com.example.projectbookshop.service.impl;

import com.example.projectbookshop.entity.Product;
import com.example.projectbookshop.repository.ProductRepository;
import com.example.projectbookshop.service.ProductService;
import com.example.projectbookshop.utilities.SessionUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {

        Optional<Product> existingProduct = productRepository.findById(product.getId());

        if (existingProduct.isPresent()) {

            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setCategory(product.getCategory());
            productRepository.save(updatedProduct);
        } else {
        }
    }

    @Override
    public void delete(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public long getTotalProductCount() {
        return productRepository.count();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }


}