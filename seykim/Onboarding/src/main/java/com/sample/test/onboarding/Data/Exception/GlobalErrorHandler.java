package com.sample.test.onboarding.Data.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
		ErrorResponse response = ex.getErrorResponse();
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
		ErrorResponse response = ErrorResponse.BAD_REQUEST;
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
}
