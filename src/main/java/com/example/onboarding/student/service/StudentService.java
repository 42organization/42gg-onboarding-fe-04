package com.example.onboarding.student.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.entity.Sugang;
import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.repository.StudentRepository;
import com.example.onboarding.alldata.repository.SugangRepository;
import com.example.onboarding.alldata.status.StudentStatus;
import com.example.onboarding.student.controller.dto.req.StudentReqDto;
import com.example.onboarding.sugang.controller.dto.req.SugangReqDto;
import com.example.onboarding.sugang.controller.dto.res.SugangResDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;
	private final SugangRepository sugangRepository;

	public Student create(StudentReqDto req) {
		if (studentRepository.existsByStudentNameAndStudentBirth(
			req.getStudentName(),
			req.getStudentBirth())) {
			throw new CustomException(ErrorCode.STUDENT_DUPLICATE);
		}
		Student student = req.toStudent();
		return studentRepository.save(student);
	}

	public void drop(StudentReqDto req) {
		Student student = studentRepository.findByStudentNameAndStudentBirth(
			req.getStudentName(),
			req.getStudentBirth()
		).orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
		student.drop();
	}

	public Page<Student> bringGraduated(PageRequest pageRequest) {
		return studentRepository.findByStudentStatus(StudentStatus.GRADUATED, pageRequest);
	}

	public SugangResDto schedule(SugangReqDto req) {
		Student student = studentRepository.findByStudentNameAndStudentBirth(
			req.getStudentName(),
			req.getStudentBirth()
		).orElseThrow(() -> new CustomException(ErrorCode.SUGANG_NOT_FOUND));
		List<Sugang> sugangs = sugangRepository.findByStudent(student);
		List<Course> courseList = sugangs.stream()
			.map(Sugang::getCourse)
			.toList();
		return SugangResDto.from(student, courseList);
	}
}
