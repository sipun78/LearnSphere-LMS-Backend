
package com.lms.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.lms.dto.LessonProgressMapper;
import com.lms.dto.LessonProgressResponseDto;
import com.lms.entity.LessonProgress;
import com.lms.exception.ResourceNotFoundException;
import com.lms.service.LessonProgressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lesson-progress")
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
public class LessonProgressController {

    private final LessonProgressService lessonProgressService;

    public LessonProgressController(
            LessonProgressService lessonProgressService) {
        this.lessonProgressService = lessonProgressService;
    }

    @PostMapping
    public LessonProgressResponseDto saveProgress(
            @Valid @RequestBody LessonProgress progress) {

        return LessonProgressMapper.toDto(
                lessonProgressService.saveProgress(progress));
    }

    @GetMapping
    public List<LessonProgressResponseDto> getAllProgress() {

        return lessonProgressService.getAllProgress()
                .stream()
                .map(LessonProgressMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LessonProgressResponseDto getProgressById(
            @PathVariable Long id) {

        LessonProgress progress = lessonProgressService
                .getProgressById(id)
                .orElseThrow(() ->
                        new  ResourceNotFoundException("Lesson progress not found"));

        return LessonProgressMapper.toDto(progress);
    }

    @PutMapping("/{id}")
    public LessonProgressResponseDto updateProgress(
            @PathVariable Long id,
            @Valid @RequestBody LessonProgress progress) {

        return LessonProgressMapper.toDto(
                lessonProgressService.updateProgress(id, progress));
    }

    @DeleteMapping("/{id}")
    public String deleteProgress(@PathVariable Long id) {

        lessonProgressService.deleteProgress(id);

        return "Lesson progress deleted successfully";
    }
}
