
package com.lms.service;

import java.util.List;
import java.util.Optional;

import com.lms.entity.CourseProgress;

public interface CourseProgressService 
{

    CourseProgress calculateProgress(Long userId, Long courseId);

    List<CourseProgress> getAllProgress();

    Optional<CourseProgress> getProgressById(Long id);
}
