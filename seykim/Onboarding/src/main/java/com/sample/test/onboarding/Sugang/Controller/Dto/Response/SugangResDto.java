package com.sample.test.onboarding.Sugang.Controller.Dto.Response;

import java.util.List;

import com.sample.test.onboarding.Course.Controller.Dto.Response.CourseResDto;

import lombok.Getter;

@Getter
public class SugangResDto {

	private final List<CourseResDto> courseList;
	private Integer currentPage;

	public SugangResDto(List<CourseResDto> courseList, int currentPage) {
		this.courseList = courseList;
		this.currentPage = currentPage;
	}
}
