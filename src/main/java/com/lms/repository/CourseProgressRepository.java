package com.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.CourseProgress;

public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long>
{
	 Optional<CourseProgress> findByUser_IdAndCourse_Id(Long userId, Long courseId);
}
