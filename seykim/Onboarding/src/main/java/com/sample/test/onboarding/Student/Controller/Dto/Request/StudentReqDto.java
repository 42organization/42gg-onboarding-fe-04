package com.sample.test.onboarding.Student.Controller.Dto.Request;

import java.time.LocalDateTime;

import com.sample.test.onboarding.Data.Entity.Student;

import lombok.Getter;

@Getter
public class StudentReqDto {

	private String name;
	private LocalDateTime birthDay;

	public Student toEntity() {
		return new Student(name, birthDay);
	}
}
