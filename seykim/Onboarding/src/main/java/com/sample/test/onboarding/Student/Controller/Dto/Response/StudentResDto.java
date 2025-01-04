package com.sample.test.onboarding.Student.Controller.Dto.Response;

import java.time.LocalDateTime;

import com.sample.test.onboarding.Data.Entity.Student;

import lombok.Getter;

@Getter
public class StudentResDto {

	private final String name;
	private final LocalDateTime birthDay;
	private final Integer currentGrade;
	private final Integer totalGrade;

	public StudentResDto(Student student) {
		this.name = student.getName();
		this.birthDay = student.getBirthDay();
		this.currentGrade = student.getCurrentGrade();
		this.totalGrade = student.getTotalGrade();
	}
}
