package jpabook.onboarding.sugang.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.repository.CourseRepository;
import jpabook.onboarding.data.repository.StudentRepository;
import jpabook.onboarding.data.repository.SugangRepository;
import jpabook.onboarding.data.status.CourseStatus;
import jpabook.onboarding.data.status.StudentStatus;
import jpabook.onboarding.data.status.SugangStatus;
import jpabook.onboarding.exception.CustomError;
import jpabook.onboarding.exception.CustomException;
import jpabook.onboarding.sugang.controller.dto.request.SugangRequestDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangCoursesResponseDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangResponseDto;
import jpabook.onboarding.util.dto.response.PageResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SugangServiceImpl implements SugangService {
	private final SugangRepository sugangRepository;
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;

	@Transactional
	@Override
	public SugangResponseDto cancelSugang(final SugangRequestDto request, final Long courseId) {
		final Student student = studentRepository.findByNameAndBirthAndStatus(request.getName(), request.getBirth(),
			StudentStatus.ENROLLED).orElseThrow(() -> new CustomException(CustomError.STUDENT_NOT_FOUND));
		final Course course = courseRepository.findByIdAndStatus(courseId, CourseStatus.REGISTERED)
			.orElseThrow(() -> new CustomException(CustomError.COURSE_NOT_FOUND));
		final Sugang sugang = sugangRepository.findByStudentAndCourse(student, course)
			.orElseThrow(() -> new CustomException(CustomError.SUGANG_NOT_FOUND));
		if (sugang.getStatus() == SugangStatus.CANCELED) {
			throw new CustomException(CustomError.SUGANG_CANCEL);
		}
		student.addCurrentGrade(-course.getGrade());
		course.addCount(-1);
		sugang.updateStatus(SugangStatus.CANCELED);
		return new SugangResponseDto(sugang);
	}

	@Override
	public SugangResponseDto createSugang(final SugangRequestDto request, final Long courseId) {
		final Student student = studentRepository.findByNameAndBirthAndStatus(request.getName(), request.getBirth(),
			StudentStatus.ENROLLED).orElseThrow(() -> new CustomException(CustomError.STUDENT_NOT_FOUND));
		final Course course = courseRepository.findByIdAndStatus(courseId, CourseStatus.REGISTERED)
			.orElseThrow(() -> new CustomException(CustomError.COURSE_NOT_FOUND));
		if (sugangRepository.findByStudentAndCourse(student, course).isPresent()) {
			throw new CustomException(CustomError.SUGANG_CREATE);
		}
		student.addCurrentGrade(course.getGrade());
		course.addCount(1);
		final Sugang sugang = new Sugang(student, course);
		sugangRepository.save(sugang);
		return new SugangResponseDto(sugang);
	}

	@Transactional(readOnly = true)
	@Override
	public PageResponseDto<SugangCoursesResponseDto> getCourses(final Pageable pageable) {
		final List<SugangCoursesResponseDto> content = courseRepository.findAll(pageable)
			.map(SugangCoursesResponseDto::new)
			.getContent();
		return new PageResponseDto<>(content);
	}
}
