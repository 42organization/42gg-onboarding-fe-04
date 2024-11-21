package com.sample.test.onboarding.Course.Controller.Dto.Response;

import java.util.List;

import com.sample.test.onboarding.Data.Entity.Course;

import lombok.Getter;

@Getter
public class CourseListResDto {

	private final List<Course> courses;

	public CourseListResDto(List<Course> courses) {
		this.courses = courses;
	}
}
