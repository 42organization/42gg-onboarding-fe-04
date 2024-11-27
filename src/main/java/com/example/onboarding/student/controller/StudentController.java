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
import com.example.onboarding.student.controller.dto.res.StudentMessageResDto;
import com.example.onboarding.student.controller.dto.res.StudentPageResDto;
import com.example.onboarding.student.controller.dto.res.StudentStatusResDto;
import com.example.onboarding.student.service.StudentService;
import com.example.onboarding.alldata.entity.Student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@PostMapping
	public ResponseEntity<StudentMessageResDto>createStudent(@RequestBody @Valid StudentReqDto request)
	{
		studentService.createStudent(request);
		StudentMessageResDto msg = new StudentMessageResDto(HttpStatus.OK.value(), "학생등록성공");
		System.out.println("----createStudent-----");
		return ResponseEntity.ok(msg);
		// return ResponseEntity.status(HttpStatus.OK).body(msg);
	}

	@PatchMapping("/drop")
	public ResponseEntity<StudentMessageResDto>dropStudent(@RequestBody StudentReqDto request)
	{
		studentService.dropStudent(request);
		StudentMessageResDto msg = new StudentMessageResDto(HttpStatus.OK.value(),"학생중퇴성공");
		return ResponseEntity.ok(msg);
	}


	@GetMapping("/graduated")
	public ResponseEntity<StudentPageResDto> bringGraduated(@RequestParam(defaultValue = "0") int page) {
		System.out.println("---page----");
		System.out.println(page);

		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Student> graduated = studentService.bringGraduated(pageRequest);
		Page<StudentStatusResDto> graduatedStudentPage = graduated.map(student ->
			new StudentStatusResDto(student.getStudentBirth(), student.getStudentName(), student.getStatus())
		);

		// StudentPageResDto responseDto = new StudentPageResDto(graduatedStudentList);
		StudentPageResDto responseDto = new StudentPageResDto(graduatedStudentPage.getContent().stream().toList());
		return ResponseEntity.ok(responseDto);
	}
}

