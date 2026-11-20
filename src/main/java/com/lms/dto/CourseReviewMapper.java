
package com.lms.dto;

import com.lms.entity.CourseReview;

public class CourseReviewMapper {

    public static CourseReviewResponseDto toDto(CourseReview courseReview) {

        CourseReviewResponseDto dto = new CourseReviewResponseDto();

        dto.setId(courseReview.getId());
        dto.setUser(UserMapper.toDto(courseReview.getUser()));
        dto.setCourse(courseReview.getCourse());
        dto.setRating(courseReview.getRating());
        dto.setReview(courseReview.getReview());

        return dto;
    }
}
