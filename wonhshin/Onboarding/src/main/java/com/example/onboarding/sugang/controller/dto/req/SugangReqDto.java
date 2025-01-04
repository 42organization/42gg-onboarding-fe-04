package com.example.onboarding.sugang.controller.dto.req;

import java.time.LocalDate;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.entity.Sugang;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SugangReqDto {
	@NotBlank
	private String studentName;
	@NotNull
	private LocalDate studentBirth;

	public Sugang toSugang(Student student, Course course) {
		return Sugang.of(student, course);
	}
}
