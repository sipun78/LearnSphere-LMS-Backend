package com.lms.service;

import java.util.List;
import java.util.Optional;

import com.lms.entity.Lesson;


public interface LessonService
{
	 Lesson saveLesson(Lesson lesson);

	    List<Lesson> getAllLessons();

	    Optional<Lesson> getLessonById(Long id);

	    Lesson updateLesson(Long id, Lesson lesson);

	    void deleteLesson(Long id);
	}

