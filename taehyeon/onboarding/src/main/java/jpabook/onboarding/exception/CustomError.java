package jpabook.onboarding.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomError {
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "찾을수 없습니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "없는 메서드 입니다."),
	CONFLICT(HttpStatus.CONFLICT, "중복 오류입니다.");

	private final HttpStatus status;
	private final String message;
}
