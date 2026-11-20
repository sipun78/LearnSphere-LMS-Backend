
package com.lms.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.CourseReviewMapper;
import com.lms.dto.CourseReviewResponseDto;
import com.lms.dto.CourseReviewUpdateRequestDto;
import com.lms.entity.CourseReview;
import com.lms.exception.ResourceNotFoundException;
import com.lms.service.CourseReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course-reviews")
@PreAuthorize("hasRole('STUDENT')")
public class CourseReviewController {

    private final CourseReviewService courseReviewService;

    public CourseReviewController(CourseReviewService courseReviewService) {
        this.courseReviewService = courseReviewService;
    }

    @PostMapping
    public CourseReviewResponseDto saveReview(
            @Valid @RequestBody CourseReview review) {

        return CourseReviewMapper.toDto(
                courseReviewService.saveReview(review));
    }

    @GetMapping
    public List<CourseReviewResponseDto> getAllReviews() {
        return courseReviewService.getAllReviews()
                .stream()
                .map(CourseReviewMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public CourseReviewResponseDto getReviewById(@PathVariable Long id) {

        CourseReview review = courseReviewService.getReviewById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        return CourseReviewMapper.toDto(review);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseReviewResponseDto> getReviewsByCourseId(
            @PathVariable Long courseId) {

        return courseReviewService.getReviewsByCourseId(courseId)
                .stream()
                .map(CourseReviewMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}")
    public CourseReviewResponseDto updateReview(
            @PathVariable Long id,
            @Valid @RequestBody CourseReviewUpdateRequestDto request) {

        CourseReview review = new CourseReview();
        review.setRating(request.getRating());
        review.setReview(request.getReview());

        return CourseReviewMapper.toDto(
                courseReviewService.updateReview(id, review));
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        courseReviewService.deleteReview(id);
        return "Course review deleted successfully";
    }
}
