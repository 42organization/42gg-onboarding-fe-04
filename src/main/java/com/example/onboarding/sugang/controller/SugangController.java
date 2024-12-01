package com.example.onboarding.sugang.controller;

import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForCalendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onboarding.course.controller.dto.res.CourseResDto;
import com.example.onboarding.sugang.controller.dto.req.SugangReqDto;
import com.example.onboarding.sugang.service.SugangService;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Table(name = "student_course")
@RestController
@RequestMapping("/sugang")
@RequiredArgsConstructor
public class SugangController {
	private final SugangService sugangService;
	@PostMapping("/{course_id}")
	public ResponseEntity<Void> enroll(@RequestBody @Valid SugangReqDto req, @PathVariable Integer course_id)
	{
		sugangService.enroll(req, course_id);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{course_id}")
	public ResponseEntity<Void> cancel(@RequestBody @Valid SugangReqDto req, @PathVariable Integer course_id)
	{
		sugangService.cancel(req, course_id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// @GetMapping("/page")
	// public ResponseEntity<Page<CourseResDto>> GetSugangList(@RequestParam(defaultValue =  "0") int page)
	// {
	// 	PageRequest pageRequest = PageRequest.of(page, 5);
	// 	Page<CourseResDto> sugangList = sugangService.getCourseList(pageRequest);
	// 	return ResponseEntity.ok(sugangList);
	// }
	@GetMapping({"/page={page}", "/page"})
	public ResponseEntity<Page<CourseResDto>> GetSugangList(
		@PathVariable(required = false) Integer page
	) {
		int pageNumber = (page == null) ? 1 : page;
		PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5);
		Page<CourseResDto> sugangList = sugangService.getCourseList(pageRequest);
		return ResponseEntity.ok(sugangList);
	}
}
