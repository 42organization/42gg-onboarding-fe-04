package com.example.onboarding.course.controller.dto.res;

import com.example.onboarding.alldata.status.CourseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseResDto {
	private int id;
	private String professorName;
	private String courseTitle;
	private int currentCount;
	private int courseGrade;
	private int maxCourseCount;
	private CourseStatus courseStatus;
}
