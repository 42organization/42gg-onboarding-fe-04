package jpabook.onboarding.student.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;

public interface StudentService {
	StudentResponseDto create(StudentRequestDto request);

	StudentResponseDto drop(StudentRequestDto request);

	Page<StudentResponseDto> getGraduates(Pageable pageable);
}