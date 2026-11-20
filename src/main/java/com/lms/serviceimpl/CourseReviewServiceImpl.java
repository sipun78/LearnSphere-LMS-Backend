
package com.lms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.entity.Course;
import com.lms.entity.CourseReview;
import com.lms.entity.User;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.CourseReviewRepository;
import com.lms.repository.CourseRepository;
import com.lms.repository.UserRepository;
import com.lms.service.CourseReviewService;

@Service
public class CourseReviewServiceImpl implements CourseReviewService {

    private final CourseReviewRepository courseReviewRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public CourseReviewServiceImpl(
            CourseReviewRepository courseReviewRepository,
            UserRepository userRepository,
            CourseRepository courseRepository) {

        this.courseReviewRepository = courseReviewRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseReview saveReview(CourseReview review) {

        User user = userRepository.findById(review.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Course course = courseRepository.findById(review.getCourse().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        review.setUser(user);
        review.setCourse(course);

        return courseReviewRepository.save(review);
    }

    @Override
    public List<CourseReview> getAllReviews() {
        return courseReviewRepository.findAll();
    }

    @Override
    public Optional<CourseReview> getReviewById(Long id) {
        return courseReviewRepository.findById(id);
    }

    @Override
    public List<CourseReview> getReviewsByCourseId(Long courseId) {
        return courseReviewRepository.findByCourse_Id(courseId);
    }

    @Override
    public CourseReview updateReview(Long id, CourseReview review) {

        CourseReview existingReview = courseReviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        existingReview.setRating(review.getRating());
        existingReview.setReview(review.getReview());

        return courseReviewRepository.save(existingReview);
    }

    @Override
    public void deleteReview(Long id) {
        courseReviewRepository.deleteById(id);
    }
}
