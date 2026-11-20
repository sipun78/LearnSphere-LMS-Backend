
package com.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.lms.dto.LoginRequestDto;
import com.lms.dto.LoginResponseDto;
import com.lms.dto.RegisterRequestDto;
import com.lms.dto.UserMapper;
import com.lms.dto.UserResponseDto;
import com.lms.entity.User;
import com.lms.entity.UserRole;
import com.lms.service.JwtService;
import com.lms.service.UserService;
import com.lms.serviceimpl.CustomUserDetailsService;

import jakarta.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthController(
            UserService userService,
            AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            JwtService jwtService) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public UserResponseDto register(
            @Valid @RequestBody RegisterRequestDto request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(UserRole.STUDENT);

        return UserMapper.toDto(userService.saveUser(user));
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @Valid @RequestBody LoginRequestDto request) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()));

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(
                            authentication.getName());

            String token = jwtService.generateToken(userDetails);

            LoginResponseDto response = new LoginResponseDto();
            response.setToken(token);
            response.setEmail(userDetails.getUsername());
            response.setRole(userDetails.getAuthorities()
                    .iterator().next().getAuthority()
                    .replace("ROLE_", ""));

            return response;

        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password");
        }
    }
}
