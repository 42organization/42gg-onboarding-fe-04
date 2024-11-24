package jpabook.onboarding.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;
import jpabook.onboarding.course.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
	private final CourseService service;

	@PostMapping
	public ResponseEntity<CourseResponseDto> createCourse(@RequestBody final CourseRequestDto request) {
		final CourseResponseDto response = service.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/delete/{courseId}")
	public ResponseEntity<CourseResponseDto> deleteCourse(@PathVariable Long courseId) {
		CourseResponseDto response = service.delete(courseId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
