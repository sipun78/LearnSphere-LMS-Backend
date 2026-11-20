package com.lms.service;

import java.util.List;
import java.util.Optional;

import com.lms.entity.Course;

public interface CourseService 
{
	   Course saveCourse(Course course);

	   List<Course> getAllCourses();

	   Optional<Course> getCourseById(Long id);

	   Course updateCourse(Long id, Course course);

	   void deleteCourse(Long id);
}
