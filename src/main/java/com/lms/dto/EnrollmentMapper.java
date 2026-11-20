
package com.lms.dto;

import com.lms.entity.Enrollment;

public class EnrollmentMapper 
{

    public static EnrollmentResponseDto toDto(Enrollment enrollment) 
    {

        EnrollmentResponseDto dto = new EnrollmentResponseDto();

        dto.setId(enrollment.getId());
        dto.setUser(UserMapper.toDto(enrollment.getUser()));
        dto.setCourse(enrollment.getCourse());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());

        return dto;
    }
}
