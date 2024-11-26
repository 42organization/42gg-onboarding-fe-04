package com.sample.test.onboarding.Student.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.test.onboarding.Course.Controller.Dto.Response.CourseListResDto;
import com.sample.test.onboarding.Student.Controller.Dto.Request.StudentReqDto;
import com.sample.test.onboarding.Student.Controller.Dto.Response.StudentListResDto;
import com.sample.test.onboarding.Student.Service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;

	// 학생 등록
	@PostMapping()
	public ResponseEntity<Void> enrollStudent(@Valid @RequestBody StudentReqDto studentReqDto) {
		studentService.enrollStudent(studentReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// 학생 중퇴
	@PatchMapping("/drop")
	public ResponseEntity<Void> dropStudent(@Valid @RequestBody StudentReqDto studentReqDto) {
		studentService.dropStudent(studentReqDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// 졸업자 명단 가져오기
	@GetMapping("/graduated")
	public StudentListResDto getGraduates() {
		return studentService.getGraduates();
	}

	// 졸업자 명단 페이지 네이션
	@GetMapping("/graduated?page={page}")
	public StudentListResDto getGraduatesPage(@Valid @PathVariable int page) {
		return studentService.getGraduatesPage(page);
	}

	// 현재 시간표 보기
	@GetMapping("/schedule")
	public CourseListResDto getSchedule(@Valid @RequestBody StudentReqDto studentReqDto) {
		return studentService.getSchedule(studentReqDto);
	}
}
