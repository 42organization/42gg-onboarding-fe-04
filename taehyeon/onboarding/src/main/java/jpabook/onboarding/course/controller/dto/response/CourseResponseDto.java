package jpabook.onboarding.course.controller.dto.response;

import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.status.CourseStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseResponseDto {
	private final long id;
	private final String professorName;
	private final String name;
	private final int count;
	private final int grade;
	private final CourseStatus status;

	public CourseResponseDto(final Course course) {
		this.id = course.getId();
		this.professorName = course.getProfessorName();
		this.name = course.getName();
		this.count = course.getCount();
		this.grade = course.getGrade();
		this.status = course.getStatus();
	}
}