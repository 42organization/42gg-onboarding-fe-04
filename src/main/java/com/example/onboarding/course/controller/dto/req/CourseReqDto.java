package com.example.onboarding.course.controller.dto.req;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.status.CourseStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseReqDto {
	@NotBlank
	private String professorName;
	@NotBlank
	private String courseTitle;

	private Integer currentCount;
	private Integer courseGrade;
	private CourseStatus courseStatus;

	public Course toCourse() {
		return Course.of(professorName, courseTitle, currentCount, courseGrade, courseStatus);
	}
}
