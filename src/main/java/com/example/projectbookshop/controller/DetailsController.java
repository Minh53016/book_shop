package com.example.projectbookshop.controller;
import com.example.projectbookshop.entity.Product;
import com.example.projectbookshop.global.GlobalData;
import com.example.projectbookshop.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DetailsController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/details-product/{productId}")
    public String showProductDetails(@PathVariable int productId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (checkLogin(request)) {
            model.addAttribute("cartCount", GlobalData.cart.size());
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                model.addAttribute("product", product.get());
                List<Product> allProducts = productRepository.findAll();
                List<Product> allProductsExceptCurrent = allProducts.stream()
                        .filter(p -> p.getId() != productId)
                        .collect(Collectors.toList());
                model.addAttribute("allProductsExceptCurrent", allProductsExceptCurrent);
            }
            return "details-product";
        } else {
            return "redirect:/login";
        }
    }

    private boolean checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null && session.getAttribute("user") != null);
    }
}
