package jpabook.onboarding.sugang.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jpabook.onboarding.sugang.controller.dto.request.SugangRequestDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangResponseDto;
import jpabook.onboarding.sugang.service.SugangService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sugangs")
@RequiredArgsConstructor
public class SugangController {
	private final SugangService service;

	@PostMapping("/{courseId}")
	public ResponseEntity<SugangResponseDto> createSugang(@RequestBody final SugangRequestDto request,
		@PathVariable final Long courseId) {
		final SugangResponseDto response = service.createSugang(request, courseId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{courseId}")
	public ResponseEntity<SugangResponseDto> cancelSugang(@RequestBody final SugangRequestDto request,
		@PathVariable final Long courseId) {
		final SugangResponseDto response = service.cancelSugang(request, courseId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
