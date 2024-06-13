package gg.onboarding.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import gg.onboarding.data.dto.course.request.CourseReqDto;
import gg.onboarding.data.dto.course.request.SortReqDto;
import gg.onboarding.data.dto.course.response.CourseListResDto;
import gg.onboarding.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
	private final CourseService courseService;

	@GetMapping
	public ResponseEntity<CourseListResDto> allCourseList(@Valid @RequestBody SortReqDto sortReqDto) {
		return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses(sortReqDto));
	}

	@PostMapping
	public ResponseEntity<Void> createCourse(@Valid	@RequestBody CourseReqDto courseReqDto) {
		courseService.createCourse(courseReqDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping("/{course_id}")
	public ResponseEntity<Void> patchCourse(@PathVariable("course_id") Long courseId, @Valid @RequestBody CourseReqDto courseReqDto) {
		courseService.patchCourse(courseId, courseReqDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/{course_id}/delete")
	public ResponseEntity<Void> deleteCourse(@PathVariable("course_id") Long courseId) {
		courseService.deleteCourse(courseId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/{course_id}/finish")
	public ResponseEntity<Void> finishCourse(@PathVariable("course_id") Long courseId) {
		courseService.finishCourse(courseId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
