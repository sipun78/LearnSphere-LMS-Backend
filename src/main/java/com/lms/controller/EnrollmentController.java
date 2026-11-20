package com.lms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.EnrollmentMapper;
import com.lms.dto.EnrollmentResponseDto;
import com.lms.entity.Enrollment;
import com.lms.entity.User;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.UserRepository;
import com.lms.service.EnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enrollments")
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final UserRepository userRepository;

    public EnrollmentController(EnrollmentService enrollmentService,
                                UserRepository userRepository) {
        this.enrollmentService = enrollmentService;
        this.userRepository = userRepository;
    }

    private Long getCurrentUserId(Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
        		.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return user.getId();
    }

    @PostMapping
    public EnrollmentResponseDto saveEnrollment(
            @RequestBody Enrollment enrollment,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            Long userId = getCurrentUserId(authentication);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new  ResourceNotFoundException("User not found"));

            enrollment.setUser(user);
        }

        return EnrollmentMapper.toDto(
                enrollmentService.saveEnrollment(enrollment));
    }

    @GetMapping
    public List<EnrollmentResponseDto> getAllEnrollments(
            Authentication authentication) {

        List<Enrollment> enrollments;

        if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {

            enrollments = enrollmentService.getAllEnrollments();

        } else {

            Long userId = getCurrentUserId(authentication);

            enrollments = enrollmentService.getEnrollmentsByUserId(userId);
        }

        return enrollments.stream()
                .map(EnrollmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EnrollmentResponseDto getEnrollmentById(
            @PathVariable Long id,
            Authentication authentication) {

        Enrollment enrollment;

        if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {

            enrollment = enrollmentService.getEnrollmentById(id)
                    .orElseThrow(() -> new  ResourceNotFoundException("Enrollment not found"));

        } else {

            Long userId = getCurrentUserId(authentication);

            enrollment = enrollmentService.getEnrollmentById(id)
                    .filter(e -> e.getUser().getId().equals(userId))
                    .orElseThrow(() -> new  ResourceNotFoundException("Enrollment not found"));
        }

        return EnrollmentMapper.toDto(enrollment);
    }
    @PutMapping("/{id}")
    public EnrollmentResponseDto updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody Enrollment enrollment,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            Long userId = getCurrentUserId(authentication);

            Enrollment existingEnrollment = enrollmentService
                    .getEnrollmentById(id)
                    .filter(e -> e.getUser().getId().equals(userId))
                    .orElseThrow(() -> new  ResourceNotFoundException("Enrollment not found"));

            enrollment.setUser(existingEnrollment.getUser());
        }

        return EnrollmentMapper.toDto(
                enrollmentService.updateEnrollment(id, enrollment));
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(
            @PathVariable Long id,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            Long userId = getCurrentUserId(authentication);

            enrollmentService.getEnrollmentById(id)
                    .filter(e -> e.getUser().getId().equals(userId))
                    .orElseThrow(() -> new  ResourceNotFoundException("Enrollment not found"));
        }

        enrollmentService.deleteEnrollment(id);
    }
}