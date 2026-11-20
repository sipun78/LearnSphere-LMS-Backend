package com.lms.service;

import java.util.List;
import java.util.Optional;

import com.lms.entity.LessonProgress;

public interface LessonProgressService 
{
	    LessonProgress saveProgress(LessonProgress progress);

	    List<LessonProgress> getAllProgress();

	    Optional<LessonProgress> getProgressById(Long id);

	    LessonProgress updateProgress(Long id, LessonProgress progress);

	    void deleteProgress(Long id);
}
