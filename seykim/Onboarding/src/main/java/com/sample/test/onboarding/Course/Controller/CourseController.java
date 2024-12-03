package com.sample.test.onboarding.Course.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.test.onboarding.Course.Controller.Dto.Request.CourseReqDto;
import com.sample.test.onboarding.Course.Service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;

	@PostMapping()
	public ResponseEntity<Void> createCourse(@Valid @RequestBody CourseReqDto courseReqDto) {
		courseService.createCourse(courseReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{courseId}")
	public ResponseEntity<Void> updateCourse(@Valid @PathVariable Long courseId,
		@RequestBody CourseReqDto courseReqDto) {
		courseService.updateCourse(courseId, courseReqDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping("/delete/{courseId}")
	public ResponseEntity<Void> deleteCourse(@Valid @PathVariable Long courseId) {
		courseService.deleteCourse(courseId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/complete/{courseId}")
	public ResponseEntity<Void> completeCourse(@Valid @PathVariable Long courseId) {
		courseService.completeCourse(courseId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
