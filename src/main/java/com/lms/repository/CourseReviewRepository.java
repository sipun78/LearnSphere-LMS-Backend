
package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.CourseReview;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> 
{

    List<CourseReview> findByCourse_Id(Long courseId);
}
