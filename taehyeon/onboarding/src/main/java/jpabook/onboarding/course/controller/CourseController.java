package jpabook.onboarding.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.request.CourseUpdateRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;
import jpabook.onboarding.course.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
	private final CourseService courseService;

	@PostMapping
	public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody final CourseRequestDto request) {
		final CourseResponseDto response = courseService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{courseId}")
	public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable final Long courseId,
		@Valid @RequestBody final CourseUpdateRequestDto request) {
		final CourseResponseDto response = courseService.update(courseId, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PatchMapping("/delete/{courseId}")
	public ResponseEntity<CourseResponseDto> deleteCourse(@PathVariable final Long courseId) {
		final CourseResponseDto response = courseService.delete(courseId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PatchMapping("/complete/{courseId}")
	public ResponseEntity<CourseResponseDto> completeCourse(@PathVariable final Long courseId) {
		final CourseResponseDto response = courseService.complete(courseId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}