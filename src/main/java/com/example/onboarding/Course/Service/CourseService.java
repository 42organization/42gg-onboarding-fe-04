package com.example.onboarding.Course.Service;

import java.util.Optional;
import java.util.zip.CheckedOutputStream;

import org.springframework.stereotype.Service;

import com.example.onboarding.Course.Controller.DTO.req.CourseReqDto;
import com.example.onboarding.Course.Controller.DTO.req.CourseUpdateReqDto;
import com.example.onboarding.data.Status.CourseStatus;
import com.example.onboarding.data.repository.CourseRepository;
import com.example.onboarding.data.entity.Course;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;

	public Course createCourse(CourseReqDto req)
	{
		Course course = new Course(
			req.getProfessorName(),
			req.getCourseTitle(),
			req.getCurrentCount()
		);
		return courseRepository.save(course);
	}

	public Course updateCourse(int id, CourseUpdateReqDto request) {
		Optional<Course> optionalCourse = courseRepository.findById(id);

		Course course = optionalCourse.get();
		course.setCourseTitle(request.getCourseTitle());
		course.setProfessorName(request.getProfessorName());
		course.setGrade(request.getGrade());

		CourseStatus updatedCourseStatus = null;
		switch (request.getIsActive()){
			case "완료됨" -> updatedCourseStatus = CourseStatus.EXPIRED;
			case "삭제됨"-> updatedCourseStatus = CourseStatus.DELETE;
			case "등록됨"-> updatedCourseStatus = CourseStatus.REGISTERED;
		}
		course.setIsActive(updatedCourseStatus);
		return course;
	}

	public void deleteCourse(Integer id) {
		Optional<Course> optionalCourse = courseRepository.findById(id);
		Course course = optionalCourse.get();
		course.setIsActive(CourseStatus.DELETE);

		// if (course.getIsActive() == CourseStatus.DELETE) {
		// 	throw new IllegalStateException("강의가 이미 삭제되었습니다");
		// }
		// course.setIsActive(CourseStatus.DELETE);
		// courseRepository.save(course);
	}

	public void completeCourse(Integer id) {
		Optional<Course> optionalCourse = courseRepository.findById(id);
		Course course = optionalCourse.get();
		course.setIsActive(CourseStatus.EXPIRED);
	}
}
