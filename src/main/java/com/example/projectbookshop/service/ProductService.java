package com.example.projectbookshop.service;
import com.example.projectbookshop.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(int id);

    void save(Product product);

    void update(Product product);

    void delete(int productId);

    long getTotalProductCount();


}