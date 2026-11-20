
package com.lms.dto;

import com.lms.entity.Course;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class CourseReviewResponseDto {

    private Long id;
    private UserResponseDto user;
    private Course course;
    private Integer rating;
    private String review;
}
