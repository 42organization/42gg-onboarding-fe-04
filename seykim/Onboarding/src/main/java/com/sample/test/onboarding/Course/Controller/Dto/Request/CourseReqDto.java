package com.sample.test.onboarding.Course.Controller.Dto.Request;

import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Status.CourseStatus;

import lombok.Getter;

@Getter
public class CourseReqDto {

	private String professorName;

	private String courseName;

	private Integer curCount;

	private Integer grade;

	private CourseStatus status;

	public Course toEntity(String professorName, String courseName) {
		return new Course(professorName, courseName);
	}

	public Course toEntity(String professorName, String courseName, Integer curCount, Integer grade,
		CourseStatus status) {
		return new Course(professorName, courseName, curCount, grade, status);
	}
}