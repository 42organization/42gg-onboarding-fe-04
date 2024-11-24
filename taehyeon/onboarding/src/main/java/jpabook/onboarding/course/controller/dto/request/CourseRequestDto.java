package jpabook.onboarding.course.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseRequestDto {
	@NotBlank(message = "교수 이름은 필수입니다.")
	private final String professorName;

	@NotBlank(message = "강의 이름은 필수입니다.")
	private final String name;

	@NotBlank(message = "현재 수강 인원은 필수입니다.")
	private final int count;
}