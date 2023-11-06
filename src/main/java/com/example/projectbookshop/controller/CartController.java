package com.example.projectbookshop.controller;


import com.example.projectbookshop.global.GlobalData;
import com.example.projectbookshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @GetMapping("/cart")
    public String cartGet(Model model, HttpServletRequest request) {
        if (checkLogin(request)) {
            HttpSession session = request.getSession();
            model.addAttribute("cartCount", GlobalData.cart.size());
            double totalPrice = GlobalData.cart.stream().mapToDouble(product -> product.getPrice().doubleValue()).sum();
            model.addAttribute("total", totalPrice);
            model.addAttribute("cart", GlobalData.cart);
            return "cart";
        } else {
            return "redirect:/login";
        }
    }
        @GetMapping("/addToCart/{id}")
        public String addToCart ( @PathVariable int id){
            GlobalData.cart.add(productService.getProductById(id).get());
            return "redirect:/";
        }

        @GetMapping("/cart/removeItem/{index}")
        public String cartItemRemove ( @PathVariable int index){
            GlobalData.cart.remove(index);
            return "redirect:/cart";
        }

        @GetMapping("/checkout")
        public String checkout (Model model){
            model.addAttribute("cartCount", GlobalData.cart.size());
            double totalPrice = GlobalData.cart.stream().mapToDouble(product -> product.getPrice().doubleValue()).sum();
            model.addAttribute("total", totalPrice);
            return "checkout";
        }

        private boolean checkLogin(HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            return (session != null && session.getAttribute("user") != null);
        }
}
