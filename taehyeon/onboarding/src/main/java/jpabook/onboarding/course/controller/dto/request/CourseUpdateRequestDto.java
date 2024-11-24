package jpabook.onboarding.course.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jpabook.onboarding.data.status.CourseStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseUpdateRequestDto {
	@NotBlank(message = "교수 이름은 필수 입니다.")
	private final String professorName;

	@NotBlank(message = "강의 이름은 필수 입니다.")
	private final String name;

	@NotBlank(message = "학점은 필수 입니다.")
	private final int grade;

	@NotBlank(message = "강의 상태는 필수 입니다.")
	private final CourseStatus status;
}
