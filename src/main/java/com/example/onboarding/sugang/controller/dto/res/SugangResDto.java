package com.example.onboarding.sugang.controller.dto.res;

import java.time.LocalDate;
import java.util.List;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.status.StudentStatus;
import com.example.onboarding.course.controller.dto.res.CourseResDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SugangResDto {
	private String studentName;
	private LocalDate studentBirth;
	private int currentGrade;
	private int totalGrade;
	private StudentStatus studentStatus;
	private List<CourseResDto> courses;

	public static SugangResDto from(Student student, List<Course> course) {
		return new SugangResDto(
			student.getStudentName(),
			student.getStudentBirth(),
			student.getCurrentGrade(),
			student.getTotalGrade(),
			student.getStudentStatus(),
			course.stream()
				.map(CourseResDto::from)
				.toList()
		);
	}
}

