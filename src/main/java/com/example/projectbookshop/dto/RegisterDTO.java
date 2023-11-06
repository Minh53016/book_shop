package com.example.projectbookshop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotEmpty(message = "Vui lòng nhập tên người dùng")
    private String username;
    private String ho_ten;
    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    private String password;
    private String gioi_tinh;
    private String sdt;
    private String email;
    private String dia_chi;
    private Integer role;


}
