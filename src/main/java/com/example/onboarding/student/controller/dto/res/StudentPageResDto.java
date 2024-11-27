package com.example.onboarding.student.controller.dto.res;

import java.util.List;

import lombok.Getter;

@Getter
public class StudentPageResDto {

	// private final Page<StudentStatusResDto> students;
	private final List<StudentStatusResDto> students;

	// public StudentPageResDto(Page<StudentStatusResDto> students)
	// {
	// 	this.students = students;
	// }
	public StudentPageResDto(List<StudentStatusResDto> students)
	{
		this.students = students;
	}
}
