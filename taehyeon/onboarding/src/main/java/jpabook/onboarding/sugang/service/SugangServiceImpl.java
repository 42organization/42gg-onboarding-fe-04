package jpabook.onboarding.sugang.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.repository.CourseRepository;
import jpabook.onboarding.data.repository.StudentRepository;
import jpabook.onboarding.data.repository.SugangRepository;
import jpabook.onboarding.data.status.SugangStatus;
import jpabook.onboarding.sugang.controller.dto.request.SugangRequestDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangCoursesResponseDto;
import jpabook.onboarding.sugang.controller.dto.response.SugangResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SugangServiceImpl implements SugangService {
	private final SugangRepository repository;
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;

	@Transactional
	@Override
	public SugangResponseDto cancelSugang(final SugangRequestDto request, final Long courseId) {
		final Optional<Student> student = studentRepository.findByNameAndBirth(request.getName(), request.getBirth());
		final Optional<Course> course = courseRepository.findById(courseId);
		if (student.isEmpty() || course.isEmpty()) {
			return null;
		}
		final Optional<Sugang> sugang = repository.findByStudentAndCourse(student.get(), course.get());
		sugang.get().updateStatus(SugangStatus.CANCELED);
		return new SugangResponseDto(sugang.get());
	}

	@Override
	public SugangResponseDto createSugang(final SugangRequestDto request, final Long courseId) {
		final Optional<Student> student = studentRepository.findByNameAndBirth(request.getName(), request.getBirth());
		final Optional<Course> course = courseRepository.findById(courseId);
		if (student.isEmpty() || course.isEmpty()) {
			return null;
		}
		final Sugang sugang = new Sugang(student.get(), course.get());
		repository.save(sugang);
		return new SugangResponseDto(sugang);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<SugangCoursesResponseDto> getCourses(final Pageable pageable) {
		return courseRepository.findAll(pageable).map(SugangCoursesResponseDto::new);
	}
}
