
package com.lms.dto;

import com.lms.entity.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private UserRole role;
}
