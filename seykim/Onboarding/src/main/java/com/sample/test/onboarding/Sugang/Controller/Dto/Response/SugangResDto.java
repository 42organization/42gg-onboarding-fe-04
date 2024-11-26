package com.sample.test.onboarding.Sugang.Controller.Dto.Response;

import java.util.List;

import com.sample.test.onboarding.Data.Entity.Course;

import lombok.Getter;

@Getter
public class SugangResDto {

	private final List<Course> courseList;
	private Integer currentPage;

	public SugangResDto(List<Course> courseList, int currentPage) {
		this.courseList = courseList;
		this.currentPage = currentPage;
	}
}
