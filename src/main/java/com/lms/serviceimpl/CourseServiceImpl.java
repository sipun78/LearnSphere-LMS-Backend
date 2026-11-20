package com.lms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.entity.Course;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.CourseRepository;
import com.lms.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService
{
	 private final CourseRepository courseRepository;
	 
	  public CourseServiceImpl(CourseRepository courseRepository) 
	  {
	        this.courseRepository = courseRepository;
	  }
	 @Override
	public Course saveCourse(Course course) 
	{
		 return courseRepository.save(course);
		
	}

	@Override
	public List<Course> getAllCourses() {
		
		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> getCourseById(Long id) {
	
		return  courseRepository.findById(id);
	}

	@Override
	public Course updateCourse(Long id, Course course){
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setDuration(course.getDuration());
        existingCourse.setPrice(course.getPrice());

        return courseRepository.save(existingCourse);
    }

	@Override
	public void deleteCourse(Long id) 
	{
		
		courseRepository.deleteById(id);
	}

}
