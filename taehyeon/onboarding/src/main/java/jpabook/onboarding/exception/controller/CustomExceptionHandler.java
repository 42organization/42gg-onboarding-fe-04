package jpabook.onboarding.exception.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jpabook.onboarding.exception.CustomError;
import jpabook.onboarding.exception.CustomException;
import jpabook.onboarding.exception.controller.dto.ErrorResponseDto;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ErrorResponseDto> handleCustom400Exception(final CustomException exception) {
		return ErrorResponseDto.toResponseEntity(exception);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected HttpEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
		final MethodArgumentNotValidException exception) {
		return ErrorResponseDto.toResponseEntity(new CustomException(CustomError.BAD_REQUEST));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected HttpEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(
		final MethodArgumentTypeMismatchException exception) {
		return ErrorResponseDto.toResponseEntity(new CustomException(CustomError.BAD_REQUEST));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected HttpEntity<ErrorResponseDto> handleHttpMessageNotReadableException(final Exception exception) {
		return ErrorResponseDto.toResponseEntity(new CustomException(CustomError.BAD_REQUEST));
	}

	@ExceptionHandler(NoSuchElementException.class)
	protected HttpEntity<ErrorResponseDto> handleNoSuchElementException(final Exception exception) {
		return ErrorResponseDto.toResponseEntity(new CustomException(CustomError.NOT_FOUND));
	}

	// @ExceptionHandler(Exception.class)
	// protected HttpEntity<ErrorResponseDto> handleException(final Exception exception) {
	// 	return ErrorResponseDto.toResponseEntity(new CustomException(CustomError.NOT_FOUND));
	// }
}
