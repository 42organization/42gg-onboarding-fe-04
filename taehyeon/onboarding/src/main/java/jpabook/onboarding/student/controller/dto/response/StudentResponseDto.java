package jpabook.onboarding.student.controller.dto.response;

import java.time.LocalDate;

import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.status.StudentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentResponseDto {

	private final String name;
	private final LocalDate birth;
	private final int currentGrade;
	private final int totalGrade;
	private final StudentStatus status;

	public StudentResponseDto(final Student student) {
		this.name = student.getName();
		this.birth = student.getBirth();
		this.currentGrade = student.getCurrentGrade();
		this.totalGrade = student.getTotalGrade();
		this.status = student.getStatus();
	}
}