package com.example.onboarding.alldata.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	STUDENT_NOT_FOUND("학생을 찾을 수 없습니다", HttpStatus.NOT_FOUND),
	STUDENT_DUPLICATE("이미 존재하는 학생입니다.", HttpStatus.CONFLICT),
	STUDENT_NOT_CHANGE("학생의 정보를 변경을 할 수 없습니다.", HttpStatus.NOT_FOUND),
	STUDENT_OVER_GRADE("한 학기 최대 신청 점수를 초과하였습니다.", HttpStatus.NOT_ACCEPTABLE),
	STUDENT_OVER_TOTALGRADE("총 이수 학점 점수를 초과하였습니다", HttpStatus.NOT_ACCEPTABLE),
	COURSE_DUPLICATE("이미 존재하는 강의입니다.", HttpStatus.CONFLICT),
	COURSE_NOT_FOUND("강의를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	COURSE_NOT_CHANGE("강의 변경을 할 수 없습니다.", HttpStatus.NOT_FOUND),
	COURSE_MAX_OVER("수강 가능 인원이 초과되었습니다.", HttpStatus.NOT_FOUND),
	COURSE_NOT_EXIST("존재하지 않는 강의입니다", HttpStatus.NOT_FOUND),
	SUGANG_NOT_REGISTERD("수강신청할 수 없습니다", HttpStatus.NOT_ACCEPTABLE),
	SUGANG_NOT_FOUND("수강신청 내역이 없습니다.", HttpStatus.NOT_FOUND);
	private final String message;
	private final HttpStatus status;
}
