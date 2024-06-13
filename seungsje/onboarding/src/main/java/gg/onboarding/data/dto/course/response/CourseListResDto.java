package gg.onboarding.data.dto.course.response;

import java.util.List;

public class CourseListResDto {
	List<CourseResDto> courses;

	public CourseListResDto(List<CourseResDto> courses) {
		this.courses = courses;
	}
}
