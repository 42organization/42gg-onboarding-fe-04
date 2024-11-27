package jpabook.onboarding.data.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentStatus {
	ENROLLED("재학"),
	DROP("중퇴"),
	GRADUATED("졸업");

	private final String status;
}