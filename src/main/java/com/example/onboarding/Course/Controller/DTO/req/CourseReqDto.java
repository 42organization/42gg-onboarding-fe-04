package com.example.onboarding.Course.Controller.DTO.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseReqDto {
	private String professorName;
	private String courseTitle;
	private Integer currentCount;
}
