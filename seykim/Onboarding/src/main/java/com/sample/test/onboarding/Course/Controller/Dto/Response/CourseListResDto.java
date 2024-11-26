package com.sample.test.onboarding.Course.Controller.Dto.Response;

import java.util.List;

import lombok.Getter;

@Getter
public class CourseListResDto {

	private final List<CourseResDto> courses;

	public CourseListResDto(List<CourseResDto> courses) {
		this.courses = courses;
	}
}
