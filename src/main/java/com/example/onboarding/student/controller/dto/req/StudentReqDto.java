package com.example.onboarding.student.controller.dto.req;

import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.status.StudentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentReqDto {
	@NotBlank
	private String studentName;
	@NotNull
	private Integer studentBirth;

	private Integer currentGrade;
	private Integer totalGrade;
	private StudentStatus studentStatus;

	public Student toEntity()
	{
		return new Student(studentName, studentBirth);
	}
}
