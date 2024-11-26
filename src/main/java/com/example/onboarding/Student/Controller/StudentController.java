package com.example.onboarding.Student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onboarding.Student.Service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	// @PostMapping
	// public ResponseEntity<?>createStudent(@RequestBody StudentReqDto request)
	// {
	// 	return ;
	// }

}
















