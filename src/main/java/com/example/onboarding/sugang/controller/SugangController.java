package com.example.onboarding.sugang.controller;

import java.util.List;

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

import com.example.onboarding.sugang.controller.dto.req.SugangReqDto;
import com.example.onboarding.sugang.controller.dto.res.SugangPageResDto;
import com.example.onboarding.sugang.controller.dto.res.SugangResDto;
import com.example.onboarding.sugang.service.SugangService;
import com.example.onboarding.alldata.entity.Sugang;

import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Table(name = "student_course")
@RestController
@RequestMapping("/sugang")
@RequiredArgsConstructor
public class SugangController {
	private final SugangService sugangService;
	//
	// @PostMapping("/{course_id}")
	// public ResponseEntity<SugangResDto> applySugang(@RequestBody @Valid SugangReqDto request, @PathVariable Integer course_id)
	// {
	// 	sugangService.applySugang(request, course_id);
	// 	SugangResDto body = new SugangResDto(HttpStatus.OK.value());
	//
	// 	return ResponseEntity.status(HttpStatus.OK).body(body);
	// }
	//
	// @PatchMapping("/{course_id}")
	// public ResponseEntity<SugangResDto> cancleSugang(@RequestBody SugangReqDto request, @PathVariable Integer course_id)
	// {
	// 	sugangService.cancleSugang(request, course_id);
	// 	SugangResDto body = new SugangResDto(HttpStatus.OK.value());
	// 	return ResponseEntity.status(HttpStatus.OK).body(body);
	// }
	//
	// // @GetMapping
	// // public ResponseEntity<SugangResDto> sugangList(@RequestParam(defaultValue = "0") int page){
	// // 	PageRequest pageRequest = PageRequest.of(page, 5);
	// // 	Page<Sugang> sugangList = sugangService.sugangList(pageRequest);
	// //
	// // 	return ResponseEntity.ok(new SugangResDto(sugangList));
	// // }
	// @GetMapping("/page")
	// public ResponseEntity<List<SugangPageResDto>> getSugangList(@RequestParam(defaultValue = "0") int page)
	// {
	// 	PageRequest pageRequest = PageRequest.of(page, 5);
	// 	Page<Sugang> sugangPage = sugangService.getSugangList(pageRequest);
	// 	// List<SugangPageResDto> sugangList = sugangPage.getContent().stream().map(sugang->SugangPageResDto(sugang)).toList();
	// 	List<SugangPageResDto> sugangList = sugangPage.getContent().stream()
	// 		.map(SugangPageResDto::new)
	// 		.toList();
	// 	return ResponseEntity.ok(sugangList);
	// }
}
