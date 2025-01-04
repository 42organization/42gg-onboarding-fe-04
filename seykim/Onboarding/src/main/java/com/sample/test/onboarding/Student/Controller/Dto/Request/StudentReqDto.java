package com.sample.test.onboarding.Student.Controller.Dto.Request;

import java.time.LocalDateTime;

import com.sample.test.onboarding.Data.Entity.Student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StudentReqDto {

	@NotBlank
	private String name;

	@NotNull
	private LocalDateTime birthDay;

	public Student toEntity() {
		return new Student(name, birthDay);
	}
}
