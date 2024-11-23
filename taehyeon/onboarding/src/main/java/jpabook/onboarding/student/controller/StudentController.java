package jpabook.onboarding.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;
import jpabook.onboarding.student.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService service;

	@PostMapping
	public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto request) {
		final StudentResponseDto response = service.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
