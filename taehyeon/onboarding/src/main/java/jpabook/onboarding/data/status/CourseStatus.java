package jpabook.onboarding.data.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CourseStatus {
	REGISTERED("등록"),
	DELETED("삭제"),
	COMPLETED("완료");

	private final String status;
}