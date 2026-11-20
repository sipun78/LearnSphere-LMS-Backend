
package com.lms.dto;

import com.lms.entity.LessonProgress;

public class LessonProgressMapper {

    public static LessonProgressResponseDto toDto(
            LessonProgress progress) {

        LessonProgressResponseDto dto =
                new LessonProgressResponseDto();

        dto.setId(progress.getId());
        dto.setUser(UserMapper.toDto(progress.getUser()));
        dto.setLesson(progress.getLesson());
        dto.setCompleted(progress.isCompleted());

        return dto;
    }
}
