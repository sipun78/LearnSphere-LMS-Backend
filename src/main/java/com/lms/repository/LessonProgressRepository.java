package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.LessonProgress;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long>
{
	long countByUser_IdAndLesson_Course_IdAndCompletedTrue(
            Long userId, Long courseId);
}
