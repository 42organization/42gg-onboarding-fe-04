package gg.onboarding.data.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

	// Student
	STUDENT_NOT_FOUND(404, "ST001", "등록되어 있지 않은 학생입니다."),
	STUDENT_DATA_INVALID(400, "ST002", "학생 데이터가 올바르지 않습니다"),
	STUDENT_ALREADY_EXISTS(409, "ST003", "이미 등록된 학생입니다."),
	STUDENT_CANNOT_DROP(400, "ST004", "중퇴할 수 없는 상태입니다.");


	private final int status;
	private final String divisionCode;
	private final String message;

	ErrorCode(final int status, final String divisionCode, final String message) {
		this.status = status;
		this.divisionCode = divisionCode;
		this.message = message;
	}
}
