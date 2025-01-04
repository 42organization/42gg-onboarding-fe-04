package com.example.onboarding.alldata.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StudentStatus {
	ACTIVE("재학"),
	DROPOUT("중퇴"),
	GRADUATED("졸업");

	private final String description;
}
