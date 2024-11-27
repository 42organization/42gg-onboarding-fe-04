package com.example.onboarding.student.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

	public Student createStudent(StudentReqDto req) {
		System.out.println("Received request " + req);
		Student student = new Student(
			req.getStudentName(),
			req.getStudentBirth()
		);
		return studentRepository.save(student);
	}

	public void dropStudent(StudentReqDto req)
	{
		Student student = studentRepository.findByStudentNameAndStudentBirth(
			req.getStudentName(),
			req.getStudentBirth()
		).get();
		student.setStatus(StudentStatus.DROPOUT);
	}

	public Page<Student> bringGraduated(PageRequest pageRequest)
	{
		return studentRepository.findByStatus(StudentStatus.GRADUATE, pageRequest);
	}

}
