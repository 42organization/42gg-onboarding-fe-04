package com.example.onboarding.sugang.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.entity.Sugang;
import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.repository.CourseRepository;
import com.example.onboarding.alldata.repository.StudentRepository;
import com.example.onboarding.alldata.repository.SugangRepository;
import com.example.onboarding.alldata.status.CourseStatus;
import com.example.onboarding.course.controller.dto.res.CourseResDto;
import com.example.onboarding.sugang.controller.dto.req.SugangReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SugangService {
	private final StudentRepository studentRepository;
	private final SugangRepository sugangRepository;
	private final CourseRepository courseRepository;

	@Transactional
	public void enroll(SugangReqDto req, int courseId) {
		Student student = studentRepository.findByStudentNameAndStudentBirth(req.getStudentName(),
			req.getStudentBirth()).orElseThrow(() ->
			new CustomException(ErrorCode.STUDENT_NOT_FOUND));
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_FOUND));
		sugangRepository.findByStudentAndCourseAndCourse_CourseStatus(
				student, course, CourseStatus.REGISTERED)
			.ifPresent(sugang -> {
				throw new CustomException(ErrorCode.SUGANG_NOT_REGISTERD);
			});
		Sugang sugang = req.toSugang(student, course);
		sugangRepository.save(sugang);
	}

	@Transactional
	public void cancel(SugangReqDto req, int courseId) {
		Student student = studentRepository.findByStudentNameAndStudentBirth(req.getStudentName(),
			req.getStudentBirth()).orElseThrow(() ->
			new CustomException(ErrorCode.STUDENT_NOT_FOUND));
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_FOUND));
		Sugang sugang = sugangRepository.findByStudentAndCourse(student, course)
			.orElseThrow(() -> new CustomException(ErrorCode.SUGANG_NOT_FOUND));
		sugang.cancel();
	}

	public Page<CourseResDto> getCourseList(PageRequest pageRequest) {
		Page<Course> courseList = courseRepository.findByCourseStatus(CourseStatus.REGISTERED, pageRequest);
		return courseList.map(CourseResDto::from);
	}
}


/*
 * if => ifPresent, isPresent
 * */