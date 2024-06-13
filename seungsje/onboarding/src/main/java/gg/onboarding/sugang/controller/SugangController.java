package gg.onboarding.sugang.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gg.onboarding.data.dto.course.response.CourseListResDto;
import gg.onboarding.data.dto.student.request.StudentReqDto;
import gg.onboarding.sugang.service.SugangService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sugang")
public class SugangController {
	private final SugangService sugangService;

	@GetMapping
	public ResponseEntity<CourseListResDto> getCourseList(){
		CourseListResDto courseListResDto = sugangService.getCourseList();
		return ResponseEntity.status(HttpStatus.OK).body(courseListResDto);
	}

	@PostMapping("/{courseId}")
	public ResponseEntity<Void> enrollCourse(@RequestParam Long courseId, @RequestBody @Valid StudentReqDto studentReqDto){
		sugangService.enrollCourse(courseId, studentReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{courseId}")
	public ResponseEntity<Void> enrollCancelCourse(@RequestParam Long courseId, @RequestBody @Valid StudentReqDto studentReqDto){
		sugangService.enrollCancelCourse(courseId, studentReqDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/history")
	public ResponseEntity<CourseListResDto> getHistoryCourse(@RequestBody @Valid StudentReqDto studentReqDto){
		CourseListResDto courseListResDto = sugangService.getHistoryCourse(studentReqDto);
		return ResponseEntity.status(HttpStatus.OK).body(courseListResDto);
	}
}
