package com.example.onboarding.course.controller.dto.res;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.status.CourseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseResDto {
	private String professorName;
	private String courseTitle;
	private Integer currentCount;
	private Integer courseGrade;
	private CourseStatus courseStatus;

	public static CourseResDto from(Course course){
		return new CourseResDto(
			course.getProfessorName(),
			course.getCourseTitle(),
			course.getCurrentCount(),
			course.getCourseGrade(),
			course.getCourseStatus()
		);
	}
}
