package com.example.onboarding.alldata.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException
{
	private final ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());  // 상위 클래스에 메시지 전달
		this.errorCode = errorCode;
	}
}
