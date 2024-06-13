package gg.onboarding.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import gg.onboarding.data.dto.course.response.CourseListResDto;
import gg.onboarding.data.dto.student.request.StudentReqDto;
import gg.onboarding.data.dto.student.response.StudentListResDto;
import gg.onboarding.data.dto.student.response.StudentResDto;
import gg.onboarding.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
	private final StudentService studentService;

	@PostMapping
	public ResponseEntity<Void> createStudent(@Valid @RequestBody StudentReqDto studentReqDto) {
		studentService.createStudent(studentReqDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping
	public ResponseEntity<StudentResDto> getStudent(@Valid @RequestBody StudentReqDto studentReqDto) {
		StudentResDto studentResDto = studentService.getStudent(studentReqDto);
		return ResponseEntity.status(HttpStatus.OK).body(studentResDto);
	}

	@GetMapping("/graduate")
	public ResponseEntity<StudentListResDto> getGraduatedStudentList() {
		StudentListResDto studentListResDto = studentService.getGraduatedStudentList();
		return ResponseEntity.status(HttpStatus.OK).body(studentListResDto);
	}

	@GetMapping("/enroll")
	public ResponseEntity<CourseListResDto> getEnrollCourse(@Valid @RequestBody StudentReqDto studentReqDto) {
		CourseListResDto courseListResDto = studentService.getEnrollCourse(studentReqDto);
		return ResponseEntity.status(HttpStatus.OK).body(courseListResDto);
	}

	@PatchMapping("/drop")
	public ResponseEntity<Void> dropSchool(@Valid @RequestBody StudentReqDto studentReqDto) {
		studentService.dropSchool(studentReqDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/finish")
	public ResponseEntity<CourseListResDto> getFinishCourse(@Valid @RequestBody StudentReqDto studentReqDto) {
		CourseListResDto courseListResDto = studentService.getFinishCourse(studentReqDto);
		return ResponseEntity.status(HttpStatus.OK).body(courseListResDto);
	}
}
