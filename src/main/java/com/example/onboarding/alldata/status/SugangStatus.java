package com.example.onboarding.alldata.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SugangStatus {
	REQUESTING("수강신청중"),
	ENROLLED("수강등록완료"),
	CANCELED("수강취소");

	private final String  description;
}
