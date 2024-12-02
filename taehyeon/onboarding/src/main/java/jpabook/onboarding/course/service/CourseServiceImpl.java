package jpabook.onboarding.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.request.CourseUpdateRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;
import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.repository.CourseRepository;
import jpabook.onboarding.data.repository.SugangRepository;
import jpabook.onboarding.data.status.CourseStatus;
import jpabook.onboarding.data.status.SugangStatus;
import jpabook.onboarding.exception.CustomError;
import jpabook.onboarding.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courseRepository;
	private final SugangRepository sugangRepository;

	@Override
	public CourseResponseDto create(final CourseRequestDto request) {
		if (courseRepository.findByNameAndProfessorName(request.getName(), request.getProfessorName()).isPresent()) {
			throw new CustomException(CustomError.COURSE_CREATE);
		}
		final Course course = new Course(request);
		courseRepository.save(course);
		return new CourseResponseDto(course);
	}

	@Transactional
	@Override
	public CourseResponseDto update(final Long courseId, final CourseUpdateRequestDto request) {
		final Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(CustomError.COURSE_NOT_FOUND));
		if (course.getStatus() != request.getStatus()) {
			if (request.getStatus() == CourseStatus.COMPLETED) {
				complete(courseId);
			}
			if (request.getStatus() == CourseStatus.DELETED) {
				delete(courseId);
			}
		}
		course.update(request);
		return new CourseResponseDto(course);
	}

	@Transactional
	@Override
	public CourseResponseDto complete(final Long courseId) {
		final Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(CustomError.COURSE_NOT_FOUND));
		if (course.getStatus() != CourseStatus.REGISTERED) {
			throw new CustomException(CustomError.COURSE_COMPLETE);
		}
		final List<Sugang> sugangs = sugangRepository.findAllByCourseIdAndStatus(courseId, SugangStatus.ONGOING);
		for (final Sugang sugang : sugangs) {
			sugang.getStudent().addTotalGrade(course.getGrade());
		}
		course.updateStatus(CourseStatus.COMPLETED);
		return new CourseResponseDto(course);
	}

	@Transactional
	@Override
	public CourseResponseDto delete(final Long courseId) {
		final Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(CustomError.COURSE_NOT_FOUND));
		if (course.getStatus() != CourseStatus.REGISTERED) {
			throw new CustomException(CustomError.COURSE_DELETE);
		}
		final List<Sugang> sugangs = sugangRepository.findAllByCourseIdAndStatus(courseId, SugangStatus.ONGOING);
		for (final Sugang sugang : sugangs) {
			sugang.updateStatus(SugangStatus.CANCELED);
			sugang.getStudent().addCurrentGrade(-course.getGrade());
		}
		course.updateStatus(CourseStatus.DELETED);
		return new CourseResponseDto(course);
	}
}