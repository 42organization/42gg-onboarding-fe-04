package jpabook.onboarding.student.service;

import org.springframework.data.domain.Pageable;

import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;
import jpabook.onboarding.student.controller.dto.response.StudentSchedulesResponseDto;
import jpabook.onboarding.util.dto.response.PageResponseDto;

public interface StudentService {
	StudentResponseDto create(StudentRequestDto request);

	StudentResponseDto drop(StudentRequestDto request);

	PageResponseDto<StudentResponseDto> getGraduates(Pageable pageable);

	StudentSchedulesResponseDto getSchedule(StudentRequestDto request);
}