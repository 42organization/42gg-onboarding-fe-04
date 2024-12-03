package jpabook.onboarding.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.repository.StudentRepository;
import jpabook.onboarding.data.status.StudentStatus;
import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;
import jpabook.onboarding.student.controller.dto.response.StudentScheduleResponseDto;
import jpabook.onboarding.student.controller.dto.response.StudentSchedulesResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	private final StudentRepository repository;

	@Override
	public StudentResponseDto create(final StudentRequestDto request) {
		final Student student = new Student(request);
		repository.save(student);
		return new StudentResponseDto(student);
	}

	@Transactional
	@Override
	public StudentResponseDto drop(final StudentRequestDto request) {
		final Optional<Student> student = repository.findByNameAndBirth(request.getName(), request.getBirth());
		if (student.isEmpty()) {
			return null;
		}
		student.get().updateStatus(StudentStatus.DROP);
		return new StudentResponseDto(student.get());
	}

	@Transactional(readOnly = true)
	@Override
	public Page<StudentResponseDto> getGraduates(final Pageable pageable) {
		return repository.findAllByStatus(StudentStatus.GRADUATED, pageable).map(StudentResponseDto::new);
	}

	@Transactional(readOnly = true)
	@Override
	public StudentSchedulesResponseDto getSchedule(final StudentRequestDto request) {
		final Optional<Student> student = repository.findByNameAndBirth(request.getName(), request.getBirth());
		final List<Sugang> sugangs = student.get().getSugangs();
		final List<StudentScheduleResponseDto> schedule = new ArrayList<>();
		for (final Sugang sugang : sugangs) {
			schedule.add(new StudentScheduleResponseDto(sugang));
		}
		return new StudentSchedulesResponseDto(schedule);
	}
}