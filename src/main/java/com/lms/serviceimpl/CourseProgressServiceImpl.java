
package com.lms.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.entity.Course;
import com.lms.entity.CourseProgress;
import com.lms.entity.User;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.CourseProgressRepository;
import com.lms.repository.CourseRepository;
import com.lms.repository.LessonProgressRepository;
import com.lms.repository.LessonRepository;
import com.lms.repository.UserRepository;
import com.lms.service.CourseProgressService;

@Service
public class CourseProgressServiceImpl implements CourseProgressService {

    private final CourseProgressRepository courseProgressRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final LessonProgressRepository lessonProgressRepository;

    public CourseProgressServiceImpl(
            CourseProgressRepository courseProgressRepository,
            UserRepository userRepository,
            CourseRepository courseRepository,
            LessonRepository lessonRepository,
            LessonProgressRepository lessonProgressRepository) {

        this.courseProgressRepository = courseProgressRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.lessonProgressRepository = lessonProgressRepository;
    }

    @Override
    public CourseProgress calculateProgress(Long userId, Long courseId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        long totalLessons = lessonRepository.countByCourse_Id(courseId);

        long completedLessons =
                lessonProgressRepository.countByUser_IdAndLesson_Course_IdAndCompletedTrue(
                        userId, courseId);

        double percentage = totalLessons == 0
                ? 0.0
                : (completedLessons * 100.0) / totalLessons;

        CourseProgress progress = courseProgressRepository
                .findByUser_IdAndCourse_Id(userId, courseId)
                .orElse(new CourseProgress());

        progress.setUser(user);
        progress.setCourse(course);
        progress.setTotalLessons((int) totalLessons);
        progress.setCompletedLessons((int) completedLessons);
        progress.setPercentage(percentage);
        progress.setLastUpdated(LocalDate.now());

        return courseProgressRepository.save(progress);
    }

    @Override
    public List<CourseProgress> getAllProgress() {
        return courseProgressRepository.findAll();
    }

    @Override
    public Optional<CourseProgress> getProgressById(Long id) {
        return courseProgressRepository.findById(id);
    }
}
