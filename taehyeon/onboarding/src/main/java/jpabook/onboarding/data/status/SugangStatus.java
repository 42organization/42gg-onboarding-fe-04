package jpabook.onboarding.data.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SugangStatus {
	ONGOING("진행중"),
	CANCELED("취소"),
	COMPLETED("완료");

	private final String status;
}