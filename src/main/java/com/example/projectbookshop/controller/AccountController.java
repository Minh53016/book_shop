package com.example.projectbookshop.controller;

import com.example.projectbookshop.dto.ChangePasswordDTO;
import com.example.projectbookshop.dto.UpdateUserDTO;
import com.example.projectbookshop.service.UserService;
import com.example.projectbookshop.utilities.SessionUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/")
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("/account")
    ModelAndView account() {
        ModelAndView mdv = new ModelAndView();
        if (SessionUtilities.getUsername() == null) {
            ModelAndView loginView = new ModelAndView("redirect:/login");
            return loginView;
        }
        mdv.setViewName("account");
        mdv.addObject("user", SessionUtilities.getUser());
        return mdv;
    }


    @GetMapping("/changepassword")
    ModelAndView changePassword(ChangePasswordDTO changePasswordDTO) {
        ModelAndView mdv = new ModelAndView();
        if (!this.userService.checkLogin()) {
            mdv.setViewName("redirect:/login");
            return mdv;
        }
        mdv.setViewName("changepassword");
        return mdv;
    }

    @PostMapping("/changePassword")
    ModelAndView changePasswordAction(ChangePasswordDTO changePasswordDTO) {
        if (changePasswordDTO.getNewPassword() != null && changePasswordDTO.getOldPassword() != null) {
            if (this.userService.changePassword(changePasswordDTO)) {
                ModelAndView accountView = this.account();
                accountView.addObject("message", "Thay đổi mật khẩu thành công");
                return accountView;
            }
        }
        ModelAndView mdv = new ModelAndView("changepassword");
        mdv.addObject("err", "Mật khẩu cũ không đúng");
        return mdv;
    }


    @PostMapping("/updateAccount")
    ModelAndView updateAccountAction(UpdateUserDTO updateUserDTO) {
        log.info("update account:{}", updateUserDTO);
        if (this.userService.updateUser(updateUserDTO)) {
            return this.account().addObject("message", "Cập nhật thành công!");
        } else {
            return this.account().addObject("message", "Có lỗi xảy ra!");
        }

    }
}
