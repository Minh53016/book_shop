package com.example.projectbookshop.service;
import com.example.projectbookshop.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategory();
    Optional<Category> getCategoryById(int categoryId);
}
