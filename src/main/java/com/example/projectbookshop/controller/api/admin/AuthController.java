package com.example.projectbookshop.controller.api.admin;
import com.example.projectbookshop.dto.LoginDTO;
import com.example.projectbookshop.dto.ResponseDTO;
import com.example.projectbookshop.service.UserService;
import com.example.projectbookshop.utilities.SessionUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginDTO info) {
        if(this.userService.adminLogin(info)) {
            return new ResponseDTO("Đăng nhập thành công", SessionUtilities.getAdmin());
        }
        return new ResponseDTO("Thông tin đăng nhập không hợp lệ", null);
    }

    @GetMapping("/logout")
    public ResponseDTO logout() {
        this.userService.adminLogout();
        return new ResponseDTO("Đăng xuất thành công",null);
    }

}
