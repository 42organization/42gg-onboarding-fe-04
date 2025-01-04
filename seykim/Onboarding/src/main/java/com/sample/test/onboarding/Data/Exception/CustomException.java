package com.sample.test.onboarding.Data.Exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private final ErrorResponse errorResponse;

	public CustomException(ErrorResponse errorResponse) {
		super(errorResponse.getMessage());
		this.errorResponse = errorResponse;
	}
}
