package jpabook.onboarding.sugang.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jpabook.onboarding.sugang.controller.dto.request.SugangRequestDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangCoursesResponseDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangResponseDto;

public interface SugangService {
	SugangResponseDto createSugang(SugangRequestDto request, Long courseId);

	SugangResponseDto cancelSugang(SugangRequestDto request, Long courseId);

	Page<SugangCoursesResponseDto> getCourses(Pageable pageable);
}