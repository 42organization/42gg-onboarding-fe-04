package jpabook.onboarding.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomError {
	//공통 에러
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "없는 메서드 입니다."),
	CONFLICT(HttpStatus.CONFLICT, "중복 오류입니다."),

	//학생 에러
	STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "학생을 찾을 수 없습니다."),
	STUDENT_CREATE(HttpStatus.CONFLICT, "이미 등록된 학생입니다.,"),
	STUDENT_DROP(HttpStatus.CONFLICT, "이미 중퇴한 학생입니다."),

	//강의 에러
	COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "강의를 찾을 수 없습니다."),
	COURSE_CREATE(HttpStatus.CONFLICT, "이미 등록된 강의입니다."),
	COURSE_COMPLETE(HttpStatus.CONFLICT,"이미 완료된 강의입니다."),
	COURSE_DELETE(HttpStatus.CONFLICT,"이미 삭제된 강의입니다."),

	//수강 에러
	SUGANG_NOT_FOUND(HttpStatus.NOT_FOUND, "수강을 찾을 수 없습니다."),
	SUGANG_CREATE(HttpStatus.CONFLICT, "이미 등록된 수강입니다,"),
	SUGANG_CANCEL(HttpStatus.CONFLICT, "이미 취소된 수강입니다.");

	private final HttpStatus status;
	private final String message;
}
