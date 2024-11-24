package jpabook.onboarding.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.repository.StudentRepository;
import jpabook.onboarding.data.status.StudentStatus;
import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;
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

	@Override
	public StudentResponseDto drop(final StudentRequestDto request) {
		final Optional<Student> student = repository.findByNameAndBirth(request.getName(), request.getBirth());
		if (student.isEmpty()) {
			return null;
		}
		student.get().setStatus(StudentStatus.DROP);
		return new StudentResponseDto(student.get());
	}
}
