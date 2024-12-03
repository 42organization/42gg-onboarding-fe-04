package jpabook.onboarding.sugang.controller.dto.response;

import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.status.SugangStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SugangResponseDto {
	private final String courseName;
	private final SugangStatus status;

	public SugangResponseDto(final Sugang sugang) {
		this.courseName = sugang.getCourse().getName();
		this.status = sugang.getStatus();
	}
}
