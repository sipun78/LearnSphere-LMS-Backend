
package com.lms.dto;

import com.lms.entity.CourseProgress;

public class CourseProgressMapper {

    public static CourseProgressResponseDto toDto(
            CourseProgress progress) {

        CourseProgressResponseDto dto =
                new CourseProgressResponseDto();

        dto.setId(progress.getId());
        dto.setUser(UserMapper.toDto(progress.getUser()));
        dto.setCourse(progress.getCourse());
        dto.setTotalLessons(progress.getTotalLessons());
        dto.setCompletedLessons(progress.getCompletedLessons());
        dto.setPercentage(progress.getPercentage());
        dto.setLastUpdated(progress.getLastUpdated());

        return dto;
    }
}
