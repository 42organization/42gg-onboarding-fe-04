package com.sample.test.onboarding.Course.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.test.onboarding.Course.Controller.Dto.Request.CourseReqDto;
import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Entity.Student;
import com.sample.test.onboarding.Data.Entity.StudentCourse;
import com.sample.test.onboarding.Data.Exception.CustomException;
import com.sample.test.onboarding.Data.Exception.ErrorResponse;
import com.sample.test.onboarding.Data.Repository.CourseRepository;
import com.sample.test.onboarding.Data.Repository.StudentCourseRepository;
import com.sample.test.onboarding.Data.Repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final StudentCourseRepository studentCourseRepository;

	@Transactional
	public void createCourse(CourseReqDto courseReqDto) {
		courseRepository.findByProfessorNameAndCourseName(courseReqDto.getProfessorName(), courseReqDto.getCourseName())
			.ifPresent(course -> {
				throw new CustomException(ErrorResponse.COURSE_ALREADY_EXISTS);
			});
		courseRepository.save(courseReqDto.toEntity(courseReqDto.getProfessorName(), courseReqDto.getCourseName()));
	}

	@Transactional
	public void updateCourse(Long courseId, CourseReqDto courseReqDto) {
		courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorResponse.COURSE_NOT_FOUND));
		courseRepository.save(courseReqDto.toEntity(courseReqDto.getProfessorName(), courseReqDto.getCourseName(),
			courseReqDto.getCurCount(), courseReqDto.getGrade(), courseReqDto.getStatus()));
	}

	@Transactional
	public void deleteCourse(Long courseId) {
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorResponse.COURSE_NOT_FOUND));
		course.delete();
		courseRepository.save(course);
	}

	@Transactional
	public void completeCourse(Long courseId) {
		List<Student> students = studentCourseRepository.findByCourseId(courseId);
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new CustomException(ErrorResponse.COURSE_NOT_FOUND));
		course.complete();

		for (Student student : students) {
			student.completeCourse(course.getGrade());
			studentRepository.save(student);
		}
		for (Student student : students) {
			StudentCourse studentCourse = studentCourseRepository.findByStudentAndCourse(student, course)
				.orElseThrow(() -> new CustomException(ErrorResponse.SUGANG_NOT_FOUND));
			studentCourse.completeStudentCourse();
			studentCourseRepository.save(studentCourse);
		}
		courseRepository.save(course);
	}
}
