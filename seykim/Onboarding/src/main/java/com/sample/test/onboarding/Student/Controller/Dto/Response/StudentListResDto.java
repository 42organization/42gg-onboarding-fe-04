package com.sample.test.onboarding.Student.Controller.Dto.Response;

import java.util.List;

import lombok.Getter;

@Getter
public class StudentListResDto {

	private final List<StudentResDto> students;
	private Integer currentPage;

	public StudentListResDto(List<StudentResDto> students) {
		this.students = students;
	}

	public StudentListResDto(List<StudentResDto> students, int currentPage) {
		this.students = students;
		this.currentPage = currentPage;
	}

}
