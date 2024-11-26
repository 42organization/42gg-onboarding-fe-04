package com.sample.test.onboarding.Course.Controller.Dto.Response;

import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Status.CourseStatus;

import lombok.Getter;

@Getter
public class CourseResDto {

	private final String professorName;

	private final String courseName;

	private final Integer currentCount;

	private final Integer grade;

	private final CourseStatus status;

	public CourseResDto(Course course) {
		this.professorName = course.getProfessorName();
		this.courseName = course.getCourseName();
		this.currentCount = course.getCurrentCount();
		this.grade = course.getGrade();
		this.status = course.getStatus();
	}

}
