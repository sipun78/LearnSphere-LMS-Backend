
package com.lms.dto;

import java.time.LocalDate;

import com.lms.entity.Course;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class EnrollmentResponseDto {

    private Long id;
    private UserResponseDto user;
    private Course course;
    private LocalDate enrollmentDate;
    private String status;
}
