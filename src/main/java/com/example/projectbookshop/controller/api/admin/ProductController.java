package com.example.projectbookshop.controller.api.admin;

import com.example.projectbookshop.entity.Category;
import com.example.projectbookshop.entity.Product;
import com.example.projectbookshop.service.CategoryService;
import com.example.projectbookshop.service.ProductService;
import com.example.projectbookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private UserService userService;

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService theProductService, CategoryService theCategoryService) {
        this.productService = theProductService;
        this.categoryService = theCategoryService;
    }


    @GetMapping("/admin/products")
    public String admin(Model model) {
        if(!this.userService.checkAdminLogin()) {
            return "redirect:/admin/login";
        }
        List<Product> products = productService.getAllProducts();
        long totalProductCount = productService.getTotalProductCount();
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("totalProductCount", totalProductCount);
        return "manage-products";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("categoryId") int categoryId, RedirectAttributes redirectAttributes) {
        Optional<Category> selectedCategory = categoryService.getCategoryById(categoryId);
        selectedCategory.ifPresent(product::setCategory);
        productService.save(product);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm sản phẩm thành công!");
        return "redirect:/admin/products";
    }

    @PostMapping("/update/{productId}")
    public String updateProduct(@ModelAttribute("product") Product product,
                                @PathVariable int productId,
                                @RequestParam int categoryId) {
        Optional<Category> selectedCategoryOptional = categoryService.getCategoryById(categoryId);
        if (selectedCategoryOptional.isPresent()) {
            Category selectedCategory = selectedCategoryOptional.get();

            product.setId(productId);



            product.setCategory(selectedCategory);
            productService.update(product);
        } else {
        }

        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.delete(productId);
        return "redirect:/admin/products";
    }

}
