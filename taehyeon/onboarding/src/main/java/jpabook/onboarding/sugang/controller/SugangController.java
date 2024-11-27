package jpabook.onboarding.sugang.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpabook.onboarding.sugang.controller.dto.request.SugangRequestDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangCoursesResponseDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangResponseDto;
import jpabook.onboarding.sugang.service.SugangService;
import jpabook.onboarding.util.PageConfig;
import jpabook.onboarding.util.dto.response.PageResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sugangs")
@RequiredArgsConstructor
public class SugangController {
	private final SugangService sugangService;

	@PostMapping("/{courseId}")
	public ResponseEntity<SugangResponseDto> createSugang(@Valid @RequestBody final SugangRequestDto request,
		@PathVariable final Long courseId) {
		final SugangResponseDto response = sugangService.createSugang(request, courseId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{courseId}")
	public ResponseEntity<SugangResponseDto> cancelSugang(@Valid @RequestBody final SugangRequestDto request,
		@PathVariable final Long courseId) {
		final SugangResponseDto response = sugangService.cancelSugang(request, courseId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping
	public ResponseEntity<PageResponseDto<SugangCoursesResponseDto>> getCourses(
		@RequestParam(defaultValue = "0") final int page) {
		final PageRequest pageRequest = PageRequest.of(page, PageConfig.SIZE.getSize());
		final PageResponseDto<SugangCoursesResponseDto> response = sugangService.getCourses(pageRequest);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
