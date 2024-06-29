package gg.onboarding.data.dto.student.response;

import java.util.List;

public class StudentListResDto {

	List<StudentResDto> students;

	public StudentListResDto(List<StudentResDto> students) {
		this.students = students;
	}
}
