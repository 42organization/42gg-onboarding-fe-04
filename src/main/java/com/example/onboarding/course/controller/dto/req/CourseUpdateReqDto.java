package com.example.onboarding.course.controller.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor //@builder 패턴으로 수정
@NoArgsConstructor
public class CourseUpdateReqDto {
	private String professorName;
	private String courseTitle;
	private Integer grade;
	private String isActive;
}
