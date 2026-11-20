
package com.lms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.entity.LessonProgress;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.LessonProgressRepository;
import com.lms.repository.UserRepository;
import com.lms.repository.LessonRepository;
import com.lms.service.LessonProgressService;

@Service
public class LessonProgressServiceImpl implements LessonProgressService {

    private final LessonProgressRepository lessonProgressRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    public LessonProgressServiceImpl(
            LessonProgressRepository lessonProgressRepository,
            UserRepository userRepository,
            LessonRepository lessonRepository) {

        this.lessonProgressRepository = lessonProgressRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public LessonProgress saveProgress(LessonProgress progress) {

        progress.setUser(
            userRepository.findById(progress.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"))
        );

        progress.setLesson(
            lessonRepository.findById(progress.getLesson().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"))
        );

        return lessonProgressRepository.save(progress);
    }

    @Override
    public List<LessonProgress> getAllProgress() {
        return lessonProgressRepository.findAll();
    }

    @Override
    public Optional<LessonProgress> getProgressById(Long id) {
        return lessonProgressRepository.findById(id);
    }

    @Override
    public LessonProgress updateProgress(Long id, LessonProgress progress) {

        LessonProgress existingProgress = lessonProgressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found"));

        existingProgress.setUser(
            userRepository.findById(progress.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"))
        );

        existingProgress.setLesson(
            lessonRepository.findById(progress.getLesson().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"))
        );

        existingProgress.setCompleted(progress.isCompleted());

        return lessonProgressRepository.save(existingProgress);
    }

    @Override
    public void deleteProgress(Long id) {
        lessonProgressRepository.deleteById(id);
    }
}