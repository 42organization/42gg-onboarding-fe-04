package com.sample.test.onboarding.Sugang.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Entity.Student;
import com.sample.test.onboarding.Data.Entity.StudentCourse;
import com.sample.test.onboarding.Data.Exception.CustomException;
import com.sample.test.onboarding.Data.Exception.ErrorResponse;
import com.sample.test.onboarding.Data.Repository.CourseRepository;
import com.sample.test.onboarding.Data.Repository.StudentCourseRepository;
import com.sample.test.onboarding.Data.Repository.StudentRepository;
import com.sample.test.onboarding.Data.Status.CourseStatus;
import com.sample.test.onboarding.Data.Status.StudentCourseStatus;
import com.sample.test.onboarding.Student.Controller.Dto.Request.StudentReqDto;
import com.sample.test.onboarding.Sugang.Controller.Dto.Response.SugangResDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SugangService {

	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	private final StudentCourseRepository studentCourseRepository;

	@Transactional
	public void enrollCourse(Long courseId, StudentReqDto studentReqDto) {

		Student student = studentRepository.findByNameAndBirthDay(studentReqDto.getName(), studentReqDto.getBirthDay())
			.orElseThrow(() -> new CustomException(ErrorResponse.STUDENT_NOT_FOUND));

		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorResponse.COURSE_NOT_FOUND));
		// 400 에러 처리
		if (course.getStatus().equals(CourseStatus.COMPLETED) || course.getStatus().equals(CourseStatus.DELETE)) {
			throw new CustomException(ErrorResponse.COURSE_ALREADY_COMPLETED_OR_DELETED);
		}
		// 409 에러 처리
		studentCourseRepository.findByStudentAndCourse(student, course).ifPresent(studentCourse -> {
			throw new CustomException(ErrorResponse.SUGANG_ALREADY_EXISTS);
		});
		student.enrollCourse(course.getGrade());
		studentRepository.save(student);
		studentCourseRepository.save(new StudentCourse(student, course, StudentCourseStatus.ONGOING));
	}

	@Transactional
	public void cancelCourse(Long courseId, StudentReqDto studentReqDto) {
		// 400 에러 처리
		Student student = studentRepository.findByNameAndBirthDay(studentReqDto.getName(), studentReqDto.getBirthDay())
			.orElseThrow(() -> new CustomException(ErrorResponse.STUDENT_NOT_FOUND));
		// 400 에러 처리
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorResponse.COURSE_NOT_FOUND));
		// 400 에러 처리
		StudentCourse studentCourse = studentCourseRepository.findByStudentAndCourse(student, course)
			.orElseThrow(() -> new CustomException(ErrorResponse.SUGANG_NOT_FOUND));

		student.cancelCourse(course.getGrade());
		studentRepository.save(student);
		studentCourse.cancel();
		studentCourseRepository.save(studentCourse);
	}

	@Transactional(readOnly = true)
	public SugangResDto getCourseListPage(int page) {
		final int size = 5;

		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Course> coursePage = courseRepository.findByStatus(CourseStatus.ACTIVE, pageable);
		return new SugangResDto(coursePage.getContent(), page);
	}
}
