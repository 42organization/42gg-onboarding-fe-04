package com.example.onboarding.student.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.student.controller.dto.req.StudentReqDto;
import com.example.onboarding.alldata.status.StudentStatus;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;

	public Student create(StudentReqDto req)
	{
		if (studentRepository.existsByStudentNameAndStudentBirth(
			req.getStudentName(),
			req.getStudentBirth())) {
			throw new CustomException(ErrorCode.STUDENT_DUPLICATE);
		}
		Student student = req.toEntity();
		return studentRepository.save(student);
	}

	public void drop(StudentReqDto req)
	{
		Student student = studentRepository.findByStudentNameAndStudentBirth(
			req.getStudentName(),
			req.getStudentBirth()
		).orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
		student.drop();
	}

	public Page<Student> bringGraduated(PageRequest pageRequest)
	{
		return studentRepository.findByStudentStatus(StudentStatus.GRADUATED, pageRequest);
	}
}
