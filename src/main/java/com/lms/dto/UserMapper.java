package com.lms.dto;

import com.lms.entity.User;

public class UserMapper 
{
	public static UserResponseDto toDto(User user) {

        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    }
}
