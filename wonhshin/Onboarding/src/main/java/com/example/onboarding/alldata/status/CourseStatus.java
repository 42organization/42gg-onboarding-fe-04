package com.example.onboarding.alldata.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CourseStatus {
	REGISTERED("강의등록"),
	COMPLETED("강의완료"),
	DELETED("강의삭제");

	private final String description;
}