package com.example.onboarding.Course.Controller.DTO.res;

import com.example.onboarding.data.entity.Course;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CourseResDto {
	// private String status;
	private Integer id;
	private String professorName;
	private String courseTitle;

	// public CourseResDto(Course course, String status) {
	// 	this.status = status;
	// 	this.id = course.getId();
	// 	this.professorName = course.getProfessorName();
	// 	this.courseTitle = course.getCourseTitle();
	// }
	//
	// public CourseResDto(String status) {
	// 	this.status = status;
	// }
}

