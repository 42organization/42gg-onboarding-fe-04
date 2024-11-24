package jpabook.onboarding.course.controller.dto.response;

import jakarta.validation.constraints.NotBlank;
import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.status.CourseStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseResponseDto {
	@NotBlank(message = "교수 이름은 필수 입니다.")
	private String professorName;

	@NotBlank(message = "강의 이름은 필수 입니다.")
	private String name;

	@NotBlank(message = "현재 수강 인원은 필수 입니다.")
	private int count;

	private int grade;
	private CourseStatus status;

	public CourseResponseDto(final Course course) {
		this.professorName = course.getProfessorName();
		this.name = course.getName();
		this.count = course.getCount();
		this.grade = course.getGrade();
		this.status = course.getStatus();
	}
}