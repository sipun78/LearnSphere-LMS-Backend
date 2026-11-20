
package com.lms.dto;

import com.lms.entity.Lesson;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LessonProgressResponseDto {

    private Long id;
    private UserResponseDto user;
    private Lesson lesson;
    private boolean completed;
}
