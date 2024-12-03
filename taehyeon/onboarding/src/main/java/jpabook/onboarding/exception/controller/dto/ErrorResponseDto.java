package jpabook.onboarding.exception.controller.dto;

import org.springframework.http.ResponseEntity;

import jpabook.onboarding.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
	private final String message;

	public static ResponseEntity<ErrorResponseDto> toResponseEntity(final CustomException exception) {
		return ResponseEntity.status(exception.getError().getStatus())
			.body(new ErrorResponseDto(exception.getError().getMessage()));
	}
}
