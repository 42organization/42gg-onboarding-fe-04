package com.sample.test.onboarding.Course.Controller.Dto.Request;

import java.util.Optional;

import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Status.CourseStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CourseReqDto {

	@NotBlank
	private String professorName;

	@NotBlank
	private String courseName;

	private Optional<Integer> curCount;

	private Optional<Integer> grade;

	private Optional<CourseStatus> status;

	public Course toEntity(String professorName, String courseName) {
		return new Course(professorName, courseName);
	}

	public Course toEntity(String professorName, String courseName, Integer curCount, Integer grade,
		CourseStatus status) {
		return new Course(professorName, courseName, curCount, grade, status);
	}
}