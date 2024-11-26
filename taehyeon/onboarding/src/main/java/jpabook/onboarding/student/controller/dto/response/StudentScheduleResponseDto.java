package jpabook.onboarding.student.controller.dto.response;

import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.status.SugangStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentScheduleResponseDto {
	private final Long id;
	private final String professorName;
	private final String courseName;
	private final int grade;
	private final SugangStatus status;

	public StudentScheduleResponseDto(final Sugang sugang) {
		this.id = sugang.getId();
		this.professorName = sugang.getCourse().getProfessorName();
		this.courseName = sugang.getCourse().getName();
		this.grade = sugang.getCourse().getGrade();
		this.status = sugang.getStatus();
	}
}
