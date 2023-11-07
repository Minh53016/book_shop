package com.example.projectbookshop.service;
import com.example.projectbookshop.dto.*;
import com.example.projectbookshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    // User management
    // Search and return list user
    public Page<UserDTO> findAllUser(String sdt, String email, String ho_ten, Pageable pageable);
    // Search and return by id
    public User findUserById(Long id);
    // Search and return info username
    public User findUserByUsername(String username);
    // Add new and update username
    public boolean saveUser(User user);
    // Check login by info login
    public boolean login(LoginDTO user);
    // Register user by RegisterDTO
    public boolean register(RegisterDTO user);
    // Update info user
    public boolean updateUser(UpdateUserDTO updateUserDTO);
    // Delete user by id
    public boolean deleteUserById(Long id);
    // Check user login
    public boolean checkLogin();
    // Change password
    public boolean changePassword(ChangePasswordDTO changePasswordDTO);
    // Update info user by admin
    public boolean updateUserByAdmin(UpdateUserDTO updateUserDTO,Long id);
    // Check admin login by info
    public boolean adminLogin(LoginDTO user);
    // Check admin login
    public boolean checkAdminLogin();
    // Logout admin from system
    public void adminLogout();
    // Resetting user password
    public boolean resetPass(Long id);


}
