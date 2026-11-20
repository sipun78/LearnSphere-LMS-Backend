
package com.lms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {

    private String token;
    private String email;
    private String role;
}
