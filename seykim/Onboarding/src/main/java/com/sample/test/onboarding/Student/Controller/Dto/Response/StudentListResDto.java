package com.sample.test.onboarding.Student.Controller.Dto.Response;

import java.util.List;

import com.sample.test.onboarding.Data.Entity.Student;

import lombok.Getter;

@Getter
public class StudentListResDto {

	private final List<Student> students;
	private Integer currentPage;

	public StudentListResDto(List<Student> students) {
		this.students = students;
	}

	public StudentListResDto(List<Student> students, int currentPage) {
		this.students = students;
		this.currentPage = currentPage;
	}

}
