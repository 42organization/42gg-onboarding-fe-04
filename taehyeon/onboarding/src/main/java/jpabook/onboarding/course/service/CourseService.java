package jpabook.onboarding.course.service;

import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;

public interface CourseService {
	CourseResponseDto create(CourseRequestDto request);

	CourseResponseDto delete(Long courseId);
}
