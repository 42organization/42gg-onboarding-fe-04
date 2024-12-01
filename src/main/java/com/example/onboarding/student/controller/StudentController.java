package com.example.onboarding.student.controller;

import org.springframework.data.domain.Page;
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

import com.example.onboarding.student.controller.dto.req.StudentReqDto;
import com.example.onboarding.student.controller.dto.res.StudentResDto;
import com.example.onboarding.student.service.StudentService;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.sugang.controller.dto.res.SugangResDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@PostMapping
	public ResponseEntity<Void> createStudent(@RequestBody @Valid StudentReqDto req)
	{
		studentService.create(req);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/drop")
	public ResponseEntity<Void> dropStudent(@RequestBody @Valid StudentReqDto req)
	{
		studentService.drop(req);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/graduated")
	public ResponseEntity<Page<StudentResDto>> bringGraduated(@Valid @RequestParam(defaultValue = "0") int page)
	{
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Student> graduatedStudents = studentService.bringGraduated(pageRequest);

		Page<StudentResDto> studentDtoPage = graduatedStudents.map(student ->
			new StudentResDto(
				student.getStudentName(),
				student.getStudentBirth(),
				student.getCurrentGrade(),
				student.getTotalGrade(),
				student.getStudentStatus()
			)
		);
		return ResponseEntity.ok(studentDtoPage);
	}

	@GetMapping("/schedule")
	public ResponseEntity<SugangResDto> currentSchedule(@RequestBody @Valid StudentResDto req)
	{
		studentService.schedule(req);

	}

}

