package jpabook.onboarding.course.service;

import org.springframework.stereotype.Service;

import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;
import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	private final CourseRepository repository;

	@Override
	public CourseResponseDto create(final CourseRequestDto request) {
		Course course = new Course(request);
		repository.save(course);
		return new CourseResponseDto(course);
	}
}
