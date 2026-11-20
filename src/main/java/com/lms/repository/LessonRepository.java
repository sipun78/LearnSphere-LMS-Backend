package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson,Long>
{
	long countByCourse_Id(Long courseId);
}
