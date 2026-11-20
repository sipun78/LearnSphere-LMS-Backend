
package com.lms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.entity.Enrollment;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.EnrollmentRepository;
import com.lms.repository.UserRepository;
import com.lms.repository.CourseRepository;
import com.lms.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 UserRepository userRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {

        Long userId = enrollment.getUser().getId();
        Long courseId = enrollment.getCourse().getId();

        enrollment.setUser(
            userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"))
        );

        enrollment.setCourse(
            courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"))
        );

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {

        Enrollment existingEnrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        existingEnrollment.setUser(
            userRepository.findById(enrollment.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"))
        );

        existingEnrollment.setCourse(
            courseRepository.findById(enrollment.getCourse().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"))
        );

        existingEnrollment.setEnrollmentDate(enrollment.getEnrollmentDate());
        existingEnrollment.setStatus(enrollment.getStatus());

        return enrollmentRepository.save(existingEnrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findAllByUser_Id(userId);
    }
}
