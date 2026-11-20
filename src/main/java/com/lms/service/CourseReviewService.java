
package com.lms.service;

import java.util.List;
import java.util.Optional;

import com.lms.entity.CourseReview;

public interface CourseReviewService {

    CourseReview saveReview(CourseReview review);

    List<CourseReview> getAllReviews();

    Optional<CourseReview> getReviewById(Long id);

    List<CourseReview> getReviewsByCourseId(Long courseId);

    CourseReview updateReview(Long id, CourseReview review);

    void deleteReview(Long id);
}
