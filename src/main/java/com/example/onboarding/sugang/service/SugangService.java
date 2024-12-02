package com.example.onboarding.sugang.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.entity.Sugang;
import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.repository.CourseRepository;
import com.example.onboarding.alldata.repository.StudentRepository;
import com.example.onboarding.alldata.repository.SugangRepository;
import com.example.onboarding.alldata.status.CourseStatus;
import com.example.onboarding.alldata.status.SugangStatus;
import com.example.onboarding.course.controller.dto.res.CourseResDto;
import com.example.onboarding.sugang.controller.dto.req.SugangReqDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SugangService {
	private final StudentRepository studentRepository;
	private final SugangRepository sugangRepository;
	private final CourseRepository courseRepository;

	public void enroll(SugangReqDto req, int courseId) {
		Student student = studentRepository.findByStudentNameAndStudentBirth(req.getStudentName(),
			req.getStudentBirth()).orElseThrow(() ->
			new CustomException(ErrorCode.STUDENT_NOT_FOUND));
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_FOUND));
		if (sugangRepository.findByStudent(student).stream()
			.map(Sugang::getCourse)
			.anyMatch(
				courseTmp -> courseTmp.getId() == courseId && courseTmp.getCourseStatus() == CourseStatus.REGISTERED)
		) {
			throw new CustomException(ErrorCode.SUGANG_NOT_REGISTERD);
		}
		Sugang sugang = new Sugang(student, course);
		sugangRepository.save(sugang);
	}

	public void cancel(SugangReqDto req, int courseId) {
		Student student = studentRepository.findByStudentNameAndStudentBirth(req.getStudentName(),
			req.getStudentBirth()).orElseThrow(() ->
			new CustomException(ErrorCode.STUDENT_NOT_FOUND));
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_FOUND));
		Sugang sugang = sugangRepository.findByStudentAndCourse(student, course)
			.orElseThrow(() -> new CustomException(ErrorCode.SUGANG_NOT_FOUND));
		sugang.updateSugangStatus(SugangStatus.CANCELED);
		course.minusCurrentGrade();
	}

	public Page<CourseResDto> getCourseList(PageRequest pageRequest) {
		Page<Course> courseList = courseRepository.findByCourseStatus(CourseStatus.REGISTERED, pageRequest);
		return courseList.map(CourseResDto::from);
	}
}


/*
 * if => ifPresent, isPresent
 * */