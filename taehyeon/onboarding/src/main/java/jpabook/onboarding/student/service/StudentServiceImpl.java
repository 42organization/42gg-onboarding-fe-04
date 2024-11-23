package jpabook.onboarding.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.repository.StudentRepository;
import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import jpabook.onboarding.student.controller.dto.response.StudentResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	private final StudentRepository repository;

	@Override
	public StudentResponseDto create(final StudentRequestDto requestDto) {
		final Student student = new Student(requestDto);
		repository.save(student);
		return new StudentResponseDto(student);
	}
}
