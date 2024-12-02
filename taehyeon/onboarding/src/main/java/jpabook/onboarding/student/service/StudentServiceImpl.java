package jpabook.onboarding.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.repository.StudentRepository;
import jpabook.onboarding.data.status.StudentStatus;
import jpabook.onboarding.data.status.SugangStatus;
import jpabook.onboarding.exception.CustomError;
import jpabook.onboarding.exception.CustomException;
import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;
import jpabook.onboarding.student.controller.dto.response.StudentScheduleResponseDto;
import jpabook.onboarding.student.controller.dto.response.StudentSchedulesResponseDto;
import jpabook.onboarding.util.dto.response.PageResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository;

	@Override
	public StudentResponseDto create(final StudentRequestDto request) {
		if (studentRepository.findByNameAndBirth(request.getName(), request.getBirth()).isPresent()) {
			throw new CustomException(CustomError.STUDENT_CREATE);
		}
		final Student student = new Student(request);
		studentRepository.save(student);
		return new StudentResponseDto(student);
	}

	@Transactional
	@Override
	public StudentResponseDto drop(final StudentRequestDto request) {
		final Student student = studentRepository.findByNameAndBirth(request.getName(), request.getBirth())
			.orElseThrow(() -> new CustomException(CustomError.STUDENT_NOT_FOUND));
		if (student.getStatus() == StudentStatus.DROP) {
			throw new CustomException(CustomError.STUDENT_DROP);
		}
		final Set<Sugang> sugangs = student.getSugangs();
		for (final Sugang sugang : sugangs) {
			if (sugang.getStatus() == SugangStatus.ONGOING) {
				sugang.updateStatus(SugangStatus.CANCELED);
				sugang.getCourse().addCount(-1);
				student.addCurrentGrade(sugang.getCourse().getGrade());
			}
		}
		student.updateStatus(StudentStatus.DROP);
		return new StudentResponseDto(student);
	}

	@Transactional(readOnly = true)
	@Override
	public PageResponseDto<StudentResponseDto> getGraduates(final Pageable pageable) {
		final List<StudentResponseDto> content = studentRepository.findAllByStatus(StudentStatus.GRADUATED, pageable)
			.map(StudentResponseDto::new)
			.getContent();
		return new PageResponseDto<>(content);
	}

	@Transactional(readOnly = true)
	@Override
	public StudentSchedulesResponseDto getSchedule(final StudentRequestDto request) {
		final Student student = studentRepository.findByNameAndBirth(request.getName(), request.getBirth())
			.orElseThrow(() -> new CustomException(CustomError.STUDENT_NOT_FOUND));
		final Set<Sugang> sugangs = student.getSugangs();
		final List<StudentScheduleResponseDto> schedule = new ArrayList<>();
		for (final Sugang sugang : sugangs) {
			schedule.add(new StudentScheduleResponseDto(sugang));
		}
		return new StudentSchedulesResponseDto(schedule);
	}
}