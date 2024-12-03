package com.example.onboarding.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onboarding.course.controller.dto.req.CourseReqDto;
import com.example.onboarding.course.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;

	@PostMapping
	public ResponseEntity<Void> createCourse(@RequestBody @Valid CourseReqDto req) {
		courseService.create(req);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCourse(@PathVariable Integer id, @RequestBody @Valid CourseReqDto req) {
		courseService.update(id, req);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		courseService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping("/complete/{id}")
	public ResponseEntity<Void> complete(@PathVariable Integer id) {
		courseService.complete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}