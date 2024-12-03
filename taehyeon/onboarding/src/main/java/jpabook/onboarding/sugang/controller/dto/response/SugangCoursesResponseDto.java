package jpabook.onboarding.sugang.controller.dto.response;

import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.status.CourseStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SugangCoursesResponseDto {

	private final String courseName;
	private final String professorName;
	private final int count;
	private final int grade;
	private final CourseStatus status;

	public SugangCoursesResponseDto(final Course course) {
		this.courseName = course.getName();
		this.professorName = course.getProfessorName();
		this.count = course.getCount();
		this.grade = course.getGrade();
		this.status = course.getStatus();
	}
}