package jpabook.onboarding.student.service;

import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;

public interface StudentService {
	StudentResponseDto create(StudentRequestDto requestDto);
}
