package com.example.onboarding.student.controller.dto.res;

import java.time.LocalDate;

import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.status.StudentStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentResDto {
	private String studentName;
	private LocalDate studentBirth;
	private Integer currentGrade;
	private Integer totalGrade;
	private StudentStatus studentStatus;

	public static StudentResDto from(Student student) {
		return new StudentResDto(
			student.getStudentName(),
			student.getStudentBirth(),
			student.getCurrentGrade(),
			student.getTotalGrade(),
			student.getStudentStatus()
		);
	}
}
