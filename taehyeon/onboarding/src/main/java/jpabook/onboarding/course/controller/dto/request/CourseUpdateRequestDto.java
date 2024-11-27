package jpabook.onboarding.course.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jpabook.onboarding.data.status.CourseStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseUpdateRequestDto {
	@NotBlank(message = "교수 이름은 필수입니다.")
	private final String professorName;

	@NotBlank(message = "강의 이름은 필수입니다.")
	private final String name;

	@NotNull(message = "학점은 필수입니다.")
	private final int grade;

	@NotNull(message = "강의 상태는 필수입니다.")
	private final CourseStatus status;
}
