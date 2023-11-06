package com.example.projectbookshop.controller.api.admin;

import com.example.projectbookshop.entity.Category;
import com.example.projectbookshop.entity.Product;
import com.example.projectbookshop.repository.CategoryRepository;
import com.example.projectbookshop.repository.ProductRepository;
import com.example.projectbookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private UserService userService;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CategoryController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category-products/{categoryId}")
    public String showCategoryProducts(@PathVariable int categoryId, Model model) {
        List<Product> products = productRepository.findAllByCategory_Id(categoryId);
        Optional<Category> category = categoryRepository.findById(categoryId);
        category.ifPresent(cat -> model.addAttribute("categoryName", cat.getName()));
        model.addAttribute("products", products);
        return "category-products";
    }

    @GetMapping("/admin/categories")
    public String adminCategories(Model model) {
        if(!this.userService.checkAdminLogin()) {
            return "redirect:/admin/login";
        }
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "manage-categories";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryRepository.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm danh mục thành công!");
        return "redirect:/admin/categories";
    }

    @PostMapping("/updateCategory/{categoryId}")
    public String updateCategory(@ModelAttribute("category") Category category, @PathVariable int categoryId) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if (existingCategory.isPresent()) {
            category.setId(categoryId);
            categoryRepository.save(category);
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/deleteCategory/{categoryId}")
    public String deleteCategory(@PathVariable int categoryId, RedirectAttributes redirectAttributes) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if (existingCategory.isPresent()) {
            categoryRepository.deleteById(categoryId);
            redirectAttributes.addFlashAttribute("successMessage", "Xoá danh mục thành công!");
        }
        return "redirect:/admin/categories";
    }
}
