package com.example.onboarding.student.controller.dto.req;

import java.time.LocalDate;

import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.status.StudentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentReqDto {
	@NotBlank
	private String studentName;
	@NotNull
	private LocalDate studentBirth;
	private Integer currentGrade;
	private Integer totalGrade;
	private StudentStatus studentStatus;

	public Student toStudent() {
		return Student.of(studentName, studentBirth, currentGrade, totalGrade, studentStatus);
	}

}
