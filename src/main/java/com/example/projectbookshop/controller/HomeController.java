package com.example.projectbookshop.controller;


import com.example.projectbookshop.dto.LoginDTO;
import com.example.projectbookshop.dto.RegisterDTO;
import com.example.projectbookshop.entity.Product;
import com.example.projectbookshop.global.GlobalData;
import com.example.projectbookshop.repository.ProductRepository;
import com.example.projectbookshop.service.UserService;
import com.example.projectbookshop.utilities.SessionUtilities;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public String home(Model model, HttpSession session) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("cartCount", GlobalData.cart.size());
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }

        return "home";
    }

    @GetMapping("/search")
    public String searchProductsByName(@RequestParam("productName") String productName, Model model) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(productName);
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @PostMapping("/login")
    ModelAndView loginAction(LoginDTO login) {

        ModelAndView mdv = new ModelAndView("redirect:/account");

        if (!this.userService.login(login)) {
            ModelAndView mdvErr = new ModelAndView("login");
            mdvErr.addObject("err", "Sai thông tin đăng nhập");
            return mdvErr;
        }


        mdv.addObject("user", SessionUtilities.getUser());
        return mdv;
    }


    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }


    @PostMapping("/register")
    public ModelAndView registerAction(@Valid RegisterDTO user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView mdv = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mdv.setViewName("register");
            mdv.addObject("err", "Xin vui lòng điền đầy đủ thông tin vào các ô bắt buộc!.");
        } else {
            if (this.userService.register(user)) {
                redirectAttributes.addFlashAttribute("message", "Đăng ký thành công. Vui lòng đăng nhập.");
                mdv.setViewName("redirect:/login");
            } else {
                mdv.setViewName("register");
                mdv.addObject("err", "Đăng ký thất bại");
            }
        }

        return mdv;
    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        ModelAndView loginView = new ModelAndView("redirect:/login");
        return loginView;
    }

//    @GetMapping("")
//    ModelAndView account() {
//        ModelAndView mdv = new ModelAndView();
//
//        // check xem user đã login vào hay chưa
//        if (SessionUtilities.getUsername() == null) {
//            ModelAndView loginView = new ModelAndView("redirect:/login");
//            return loginView;
//        }
//        // khi login thành công
//        mdv.setViewName("account");
//        mdv.addObject("user", SessionUtilities.getUser());
//        return mdv;
//    }

}

