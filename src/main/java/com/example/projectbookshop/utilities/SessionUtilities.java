package com.example.projectbookshop.utilities;

import com.example.projectbookshop.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtilities {
    // Return http session
    public static HttpSession getSession() {
        // Lấy ra ServletRequestAttributes từ RequestContextHolder(HTTP)
        // ServletRequestAttributes chứa thông tin yêu cầu hiện tại
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // attr lấy ra đối tượng hiện tại = method getRequest(), getSession() lấy ra đối tượng Http session
        return attr.getRequest().getSession();
    }
    public static String getUsername() {
        return (String) getSession().getAttribute("username");
    }
    public static void setUsername(String username) {
        getSession().setAttribute("username", username);
    }
    // Đặt đối tượng UserDTO vào phiên Http sesion
    public static void setUser(UserDTO user) {
        getSession().setAttribute("user", user);
    }

    public static UserDTO getUser() {
        return (UserDTO) getSession().getAttribute("user");
    }

    public static void setAdmin(UserDTO user) {
        getSession().setAttribute("admin",user);
    }

    public static UserDTO getAdmin() {
        return (UserDTO)getSession().getAttribute("admin");
    }

}
