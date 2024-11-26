package com.sample.test.onboarding.Data.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorResponse {
	//@Valid 처리 에러
	BAD_REQUEST(400, "파라미터 값을 확인해주세요", "BAD_REQUEST"),

	// 학생 에러
	STUDENT_NOT_FOUND(404, "존재하지 않는 학생입니다", "STUDENT_NOT_FOUND"),
	STUDENT_ALREADY_EXISTS(409, "이미 등록된 학생입니다", "STUDENT_ALREADY_EXISTS"),
	STUDENT_ALREADY_DROPPED(409, "이미 중퇴한 학생입니다", "STUDENT_ALREADY_DROPPED"),
	STUDENT_ALREADY_GRADUATED(409, "이미 졸업한 학생입니다", "STUDENT_ALREADY_GRADUATED"),
	STUDENT_MAXIMUM_ERROR(400, "수강 가능한 학점을 넘었습니다", "STUDENT_MAXIMUM_ERROR"),

	// 강의 에러
	COURSE_ALREADY_EXISTS(409, "이미 등록된 강의입니다", "COURSE_ALREADY_EXISTS"),
	COURSE_ALREADY_COMPLETED_OR_DELETED(400, "이미 완료거나 삭제된 강의입니다", "COURSE_ALREADY_COMPLETED_OR_DELETED"),
	COURSE_NOT_FOUND(404, "존재하지 않는 강의입니다", "COURSE_NOT_FOUND"),

	// 수강 신청 에러
	SUGANG_NOT_FOUND(400, "수강신청 내역이 존재하지 않습니다.", "SUGANG_NOT_FOUND"),
	SUGANG_ALREADY_EXISTS(409, "이미 수강신청한 내역이 존재합니다.", "SUGANG_ALREADY_EXISTS"),
	SUGANG_ALREADY_COMPLETED_OR_DELETED(400, "이미 완료거나 삭제된 강의입니다", "SUGANG_ALREADY_COMPLETED_OR_DELETED");

	private final int status;
	private final String message;
	private final String code;
}
