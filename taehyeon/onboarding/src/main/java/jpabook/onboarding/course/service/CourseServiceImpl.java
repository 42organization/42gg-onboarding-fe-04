package jpabook.onboarding.course.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.request.CourseUpdateRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;
import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.repository.CourseRepository;
import jpabook.onboarding.data.status.CourseStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	private final CourseRepository repository;

	@Override
	public CourseResponseDto create(final CourseRequestDto request) {
		final Course course = new Course(request);
		repository.save(course);
		return new CourseResponseDto(course);
	}

	@Transactional
	@Override
	public CourseResponseDto complete(final Long courseId) {
		final Optional<Course> course = repository.findById(courseId);
		if (course.isEmpty()) {
			return null;
		}
		course.get().updateStatus(CourseStatus.COMPLETED);
		return new CourseResponseDto(course.get());
	}

	@Transactional
	@Override
	public CourseResponseDto update(final Long courseId, final CourseUpdateRequestDto request) {
		final Optional<Course> course = repository.findById(courseId);
		if (course.isEmpty()) {
			final Course newCourse = repository.save(new Course(request));
			return new CourseResponseDto(newCourse);
		}
		course.get().update(request);
		return new CourseResponseDto(course.get());
	}

	@Transactional
	@Override
	public CourseResponseDto delete(final Long courseId) {
		final Optional<Course> course = repository.findById(courseId);
		if (course.isEmpty()) {
			return null;
		}
		course.get().updateStatus(CourseStatus.DELETED);
		return new CourseResponseDto(course.get());

	}
}