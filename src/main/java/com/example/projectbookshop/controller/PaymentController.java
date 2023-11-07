package com.example.projectbookshop.controller;

import com.example.projectbookshop.global.GlobalData;
import com.example.projectbookshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payNow")
    public String processPayment(Model model) {
        if (GlobalData.cart.isEmpty()) {
            return "paymentFailed";
        }
        double totalAmount = GlobalData.cart.stream().mapToDouble(product -> product.getPrice().doubleValue()).sum();
        boolean paymentSuccessful = paymentService.processPayment(totalAmount);

        if (paymentSuccessful) {
            model.addAttribute("paymentSuccessMessage", "Payment was successful!");
            return "paymentSuccess";
        } else {
            model.addAttribute("paymentFailureMessage", "Payment failed!");
            return "paymentFailed";
        }
    }
}
