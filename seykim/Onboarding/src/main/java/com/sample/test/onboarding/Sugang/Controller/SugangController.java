package com.sample.test.onboarding.Sugang.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.test.onboarding.Student.Controller.Dto.Request.StudentReqDto;
import com.sample.test.onboarding.Sugang.Controller.Dto.Response.SugangResDto;
import com.sample.test.onboarding.Sugang.Service.SugangService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sugangs")
@RequiredArgsConstructor
public class SugangController {

	private final SugangService sugangService;

	@PostMapping("/{courseId}")
	public ResponseEntity<Void> enrollCourse(@Valid @PathVariable Long courseId,
		@Valid @RequestBody StudentReqDto studentReqDto) {

		sugangService.enrollCourse(courseId, studentReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{courseId}")
	public ResponseEntity<Void> cancelCourse(@Valid @PathVariable Long courseId,
		@Valid @RequestBody StudentReqDto studentReqDto) {
		sugangService.cancelCourse(courseId, studentReqDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/page={page}")
	public SugangResDto getCourseListPage(@Valid @PathVariable int page) {
		return sugangService.getCourseListPage(page);
	}
}
