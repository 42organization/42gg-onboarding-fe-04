package com.example.onboarding.student.controller.dto.res;

import com.example.onboarding.alldata.status.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentStatusResDto {
	private Integer studentBirth;
	private String  studentName;
	private StudentStatus Stauts;
}

