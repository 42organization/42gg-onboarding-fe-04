package com.example.onboarding.course.service;

import org.springframework.stereotype.Service;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.course.controller.dto.req.CourseReqDto;
import com.example.onboarding.alldata.repository.CourseRepository;
import com.example.onboarding.alldata.entity.Course;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;

	public Course create(CourseReqDto req)
	{
		Course course = req.toCourse();
		return courseRepository.save(course);
	}

	public void update(Integer id, CourseReqDto req)
	{
		Course course = courseRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_EXIST));
		if (courseRepository.existsByProfessorNameAndCourseTitleAndIdNot(
			req.getProfessorName(), req.getCourseTitle(), id)) {
			throw new CustomException(ErrorCode.COURSE_DUPLICATE);
		}
		course.update(req);
	}

	public void delete(Integer id)
	{
		Course course = courseRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_EXIST));
		course.delete();
	}

	public void complete(Integer id)
	{
		Course course = courseRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_EXIST));
		course.complete();
	}
}