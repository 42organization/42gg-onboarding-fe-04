package com.example.onboarding.course.controller.dto.req;

import com.example.onboarding.alldata.status.CourseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEditReqDto {
	private String professorName;
	private String courseTitle;
	private int currentCount;
	private int courseGrade;
	private CourseStatus courseStatus;
}
