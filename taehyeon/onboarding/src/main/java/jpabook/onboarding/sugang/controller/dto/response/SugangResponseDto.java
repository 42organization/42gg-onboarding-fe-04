package jpabook.onboarding.sugang.controller.dto.response;

import jakarta.validation.constraints.NotBlank;
import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.status.SugangStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SugangResponseDto {
	@NotBlank(message = "과목 이름은 필수입니다.")
	private final String courseName;

	@NotBlank(message = "수강 상태는 필수입니다.")
	private final SugangStatus status;

	public SugangResponseDto(final Sugang sugang) {
		this.courseName = sugang.getCourse().getName();
		this.status = sugang.getStatus();
	}
}
