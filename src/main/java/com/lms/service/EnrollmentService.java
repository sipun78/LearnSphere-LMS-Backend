package com.lms.service;

import java.util.List;
import java.util.Optional;

import com.lms.entity.Enrollment;

public interface EnrollmentService {

    Enrollment saveEnrollment(Enrollment enrollment);

    List<Enrollment> getAllEnrollments();

    Optional<Enrollment> getEnrollmentById(Long id);

    Enrollment updateEnrollment(Long id, Enrollment enrollment);

    void deleteEnrollment(Long id);

    List<Enrollment> getEnrollmentsByUserId(Long userId);
}