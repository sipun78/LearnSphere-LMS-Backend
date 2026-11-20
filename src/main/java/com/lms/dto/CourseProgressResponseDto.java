
package com.lms.dto;

import java.time.LocalDate;

import com.lms.entity.Course;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class CourseProgressResponseDto {

    private Long id;
    private UserResponseDto user;
    private Course course;
    private Integer totalLessons;
    private Integer completedLessons;
    private Double percentage;
    private LocalDate lastUpdated;
}
