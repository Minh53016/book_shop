package com.example.projectbookshop.repository;
import com.example.projectbookshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory_Id(int categoryId);

    List<Product> findByNameContainingIgnoreCase(String name);

}
