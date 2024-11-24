package jpabook.onboarding.course.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseRequestDto {
	@NotBlank(message = "교수 이름은 필수 입니다.")
	private String professorName;

	@NotBlank(message = "강의 이름은 필수 입니다.")
	private String name;

	@NotBlank(message = "현재 수강 인원은 필수 입니다.")
	private int count;
}