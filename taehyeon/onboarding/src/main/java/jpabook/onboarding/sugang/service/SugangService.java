package jpabook.onboarding.sugang.service;

import jpabook.onboarding.sugang.controller.dto.request.SugangRequestDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangResponseDto;

public interface SugangService {
	SugangResponseDto createSugang(SugangRequestDto request, Long courseId);

	SugangResponseDto cancelSugang(SugangRequestDto request, Long courseId);
}