package com.example.projectbookshop.utilities;

import com.example.projectbookshop.dto.UserDTO;
import com.example.projectbookshop.entity.User;

public class ConvertUserToDto {
    // Convert User to UserDTO
    public static UserDTO convertUsertoDto(User user) {
        return new UserDTO(user.getId(),user.getUsername(), user.getHo_ten(), user.getSdt(), user.getGioi_tinh(), user.getEmail(),
                user.getDia_chi(), user.getRole());
    }
}
