package jpabook.onboarding.sugang.service;

import org.springframework.data.domain.Pageable;

import jpabook.onboarding.sugang.controller.dto.request.SugangRequestDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangCoursesResponseDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangResponseDto;
import jpabook.onboarding.util.dto.response.PageResponseDto;

public interface SugangService {
	SugangResponseDto createSugang(SugangRequestDto request, Long courseId);

	SugangResponseDto cancelSugang(SugangRequestDto request, Long courseId);

	PageResponseDto<SugangCoursesResponseDto> getCourses(Pageable pageable);
}