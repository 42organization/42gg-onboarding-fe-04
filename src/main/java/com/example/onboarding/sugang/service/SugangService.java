package com.example.onboarding.sugang.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.onboarding.sugang.controller.dto.req.SugangReqDto;
import com.example.onboarding.alldata.status.SugangStatus;
import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.entity.Sugang;
import com.example.onboarding.alldata.repository.CourseRepository;
import com.example.onboarding.alldata.repository.StudentRepository;
import com.example.onboarding.alldata.repository.SugangRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SugangService {
	private final StudentRepository studentRepository;
	private final SugangRepository sugangRepository;
	private final CourseRepository courseRepository;

	public Sugang applySugang(SugangReqDto req, Integer courseId)
	{
		Student studentInfo = studentRepository.findByStudentNameAndStudentBirth(req.getStudentName(), req.getStudentBirth()).orElseThrow();
		// Student student = sugangRepository.findById(studentId).orElseThrow().getStudent();
		Course course = courseRepository.findById(courseId).orElseThrow(()-> new IllegalArgumentException("course not find with id"));

		Sugang sugang = new Sugang(studentInfo, course);
		return sugangRepository.save(sugang);
	}

	public void cancleSugang(SugangReqDto req, Integer courseId)
	{
		Student studentInfo = studentRepository.findByStudentNameAndStudentBirth(req.getStudentName(), req.getStudentBirth()).orElseThrow();
		Course course = courseRepository.findById(courseId).orElseThrow();

		Sugang sugang = sugangRepository.findByStudentAndCourse(studentInfo, course).orElseThrow();
		sugang.updateSugangStatus(SugangStatus.Completed);
	}

	public Page<Sugang> getSugangList(PageRequest pageRequest)
	{
		Page<Sugang> result = sugangRepository.findByStatus(SugangStatus.Completed, pageRequest);
		System.out.println("조회된 데이터 수: " + result.getTotalElements());
		return result;
		// return sugangRepository.findByStatus(SugangStatus.Completed, pageRequest);
	}
}
