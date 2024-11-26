package com.example.onboarding.Course.Controller.DTO.req;

import com.example.onboarding.data.Status.CourseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateReqDto {
	private String professorName;
	private String courseTitle;
	private Integer grade;
	private String isActive;
}
