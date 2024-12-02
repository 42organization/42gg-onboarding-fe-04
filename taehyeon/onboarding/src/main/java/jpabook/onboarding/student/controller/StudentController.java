package jpabook.onboarding.student.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;
import jpabook.onboarding.student.controller.dto.response.StudentSchedulesResponseDto;
import jpabook.onboarding.student.service.StudentService;
import jpabook.onboarding.util.PageConfig;
import jpabook.onboarding.util.dto.response.PageResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@PostMapping
	public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody final StudentRequestDto request) {
		final StudentResponseDto response = studentService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/drop")
	public ResponseEntity<StudentResponseDto> dropStudent(@Valid @RequestBody final StudentRequestDto request) {
		final StudentResponseDto response = studentService.drop(request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/graduated")
	public ResponseEntity<PageResponseDto<StudentResponseDto>> getGraduates(
		@RequestParam(defaultValue = "0") final int page) {
		final PageRequest pageRequest = PageRequest.of(page, PageConfig.SIZE.getSize());
		final PageResponseDto<StudentResponseDto> response = studentService.getGraduates(pageRequest);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/schedule")
	public ResponseEntity<StudentSchedulesResponseDto> getSchedule(
		@Valid @RequestBody final StudentRequestDto request) {
		final StudentSchedulesResponseDto response = studentService.getSchedule(request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}