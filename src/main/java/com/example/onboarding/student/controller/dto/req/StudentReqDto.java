package com.example.onboarding.student.controller.dto.req;

import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.status.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentReqDto {
	// TODO: valid 추가하기
	private String studentName;
	private Integer studentBirth;
	private Integer currentGrade;
	private Integer totalGrade;
	private StudentStatus studentStatus;

	public Student toStudent() {
		return Student.of(studentName, studentBirth, currentGrade, totalGrade, studentStatus);
	}

}
