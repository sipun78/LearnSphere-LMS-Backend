
package com.lms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.CourseProgressMapper;
import com.lms.dto.CourseProgressResponseDto;
import com.lms.entity.CourseProgress;
import com.lms.exception.ResourceNotFoundException;
import com.lms.service.CourseProgressService;

@RestController
@RequestMapping("/api/course-progress")
@PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
public class CourseProgressController
{

    private final CourseProgressService courseProgressService;
    public CourseProgressController(CourseProgressService courseProgressService) {
        this.courseProgressService = courseProgressService;
    }

    @PostMapping("/{userId}/{courseId}")
    public CourseProgressResponseDto calculateProgress(
            @PathVariable Long userId,
            @PathVariable Long courseId) {

        return CourseProgressMapper.toDto(
                courseProgressService.calculateProgress(userId, courseId));
    }

    @GetMapping
    public List<CourseProgressResponseDto> getAllProgress() {

        return courseProgressService.getAllProgress()
                .stream()
                .map(CourseProgressMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CourseProgressResponseDto getProgressById(@PathVariable Long id)
    {

        CourseProgress progress = courseProgressService.getProgressById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Course progress not found"));

        return CourseProgressMapper.toDto(progress);
    }
}
