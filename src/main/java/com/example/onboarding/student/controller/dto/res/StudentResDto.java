package com.example.onboarding.student.controller.dto.res;

import com.example.onboarding.alldata.status.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentResDto {
	private String studentName;
	private Integer studentBirth;
	private Integer currentGrade;
	private Integer totalGrade;
	private StudentStatus studentStatus;

}
