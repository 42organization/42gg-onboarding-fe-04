package com.example.onboarding.course.controller;


import com.example.onboarding.course.controller.dto.req.CourseReqDto;
import com.example.onboarding.course.controller.dto.req.CourseUpdateReqDto;
import com.example.onboarding.course.controller.dto.res.CourseMessageResDto;
import com.example.onboarding.course.service.CourseService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService; // 생성할 때 정해지고 아니면 알 수가 없음

	@PostMapping
	// public ResponseEntity<Void> createCourse(@RequestBody CourseReqDto request) {
	public ResponseEntity<CourseMessageResDto> createCourse(@RequestBody CourseReqDto request) {
		// System.out.println(1111111);
		// Course course = new Course();
		// courseService.createCourse(request);
		courseService.createCourse(request);
		CourseMessageResDto msg = new CourseMessageResDto(HttpStatus.OK.value(),"등록성공" );
		return ResponseEntity.ok(msg);
		// CourseResDto res = new CourseResDto("등록성공!");
		// CourseResDto response = new CourseResDto("등록성공!", course.getId(),
		// 	course.getProfessorName(),
		// 	course.getCourseTitle());
		// return ResponseEntity.ok(res);
		// return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseMessageResDto> updateCourse(@PathVariable Integer id, @RequestBody CourseUpdateReqDto request) {
		try {
			courseService.updateCourse(id, request);
			CourseMessageResDto msg = new CourseMessageResDto(HttpStatus.OK.value(),"변경성공" );
			return ResponseEntity.ok(msg);
		} catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new CourseMessageResDto(400, "잘못된 정보입니다"));
		}
		// } catch (IllegalStateException e) {
		// 	return ResponseEntity.status(409).body(new CourseMessageResDto(409, "중복된 강의가 존재합니다."));
		// }
	}

	@PatchMapping("/delete/{id}")
	public ResponseEntity<CourseMessageResDto> deleteCourse(@PathVariable Integer id) {
			courseService.deleteCourse(id);
			CourseMessageResDto msg = new CourseMessageResDto(HttpStatus.OK.value(),"삭제성공" );
			return ResponseEntity.ok(msg);
	}

	@PatchMapping("/complete/{id}")
	public ResponseEntity<CourseMessageResDto> completeCourse(@PathVariable Integer id) {
		courseService.completeCourse(id);
		CourseMessageResDto msg = new CourseMessageResDto(HttpStatus.OK.value(),"완료성공" );
		return ResponseEntity.ok(msg);
	}

	// @PatchMapping("/complete/{id}")
	// public ResponseEntity<?> completeCourse(@PathVariable Integer id) {
	// 	try {
	// 		courseService.completeCourse(id);
	// 		return ResponseEntity.ok(new CourseResDto("강의삭제성공!"));
	// 	} catch (IllegalArgumentException e) {
	// 		return ResponseEntity.status(404).body(new CourseResDto("강의를 찾을 수 없습니다"));
	// 	} catch (IllegalStateException e) {
	// 		return ResponseEntity.status(409).body(new CourseResDto("강의가 이미 완료되었습니다."));
	// 	} catch (Exception e) {
	// 		return ResponseEntity.badRequest().body(new CourseResDto("강의완료등록 실패!"));
	// 	}
	// }
}