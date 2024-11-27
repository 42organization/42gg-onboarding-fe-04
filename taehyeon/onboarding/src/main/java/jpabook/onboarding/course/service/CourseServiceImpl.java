package jpabook.onboarding.course.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.request.CourseUpdateRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;
import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.repository.CourseRepository;
import jpabook.onboarding.data.status.CourseStatus;
import jpabook.onboarding.exception.CustomError;
import jpabook.onboarding.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courseRepository;

	@Override
	public CourseResponseDto create(final CourseRequestDto request) {
		if (courseRepository.findByNameAndProfessorName(request.getName(), request.getProfessorName()).isPresent()) {
			throw new CustomException(CustomError.CONFLICT);
		}
		final Course course = new Course(request);
		courseRepository.save(course);
		return new CourseResponseDto(course);
	}

	@Transactional
	@Override
	public CourseResponseDto update(final Long courseId, final CourseUpdateRequestDto request) {
		if (request.getStatus() == CourseStatus.COMPLETED
			|| courseRepository.findByNameAndProfessorNameAndGradeAndStatus(request.getName(),
			request.getProfessorName(), request.getGrade(), request.getStatus()).isPresent()) {
			throw new CustomException(CustomError.CONFLICT);
		}
		final Course course = courseRepository.findById(courseId).orElseThrow();
		course.update(request);
		return new CourseResponseDto(course);
	}

	@Transactional
	@Override
	public CourseResponseDto complete(final Long courseId) {
		final Course course = courseRepository.findById(courseId).orElseThrow();
		if (course.getStatus() == CourseStatus.COMPLETED) {
			throw new CustomException(CustomError.CONFLICT);
		}
		course.updateStatus(CourseStatus.COMPLETED);
		return new CourseResponseDto(course);
	}

	@Transactional
	@Override
	public CourseResponseDto delete(final Long courseId) {
		final Course course = courseRepository.findById(courseId).orElseThrow();
		if (course.getStatus() == CourseStatus.DELETED || course.getStatus() == CourseStatus.COMPLETED) {
			throw new CustomException(CustomError.CONFLICT);
		}
		course.updateStatus(CourseStatus.DELETED);
		return new CourseResponseDto(course);

	}
}