package com.example.onboarding.alldata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.example.onboarding.alldata.status.CourseStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "professor_name", nullable = false)
	@NotBlank(message = "교수 이름은 필수입니다")
	private String professorName;

	@Column(name = "course_title", nullable = false)
	@NotBlank(message = "강의명은 필수입니다")
	private String courseTitle;

	@Column(name = "current_count", nullable = false)
	private int currentCount;

	@Column
	private int courseGrade;

	@Column(name = "max_course_count")
	private int maxCourseCount;

	@Enumerated(EnumType.STRING)
	@Column(name = "course_status")
	private CourseStatus courseStatus;

	public Course(String professorName, String courseTitle)
	{
		this.professorName = professorName;
		this.courseTitle = courseTitle;
		this.currentCount = 0;
		this.courseGrade = 0;
		this.maxCourseCount = 10;
		this.courseStatus = CourseStatus.REGISTERED;
	}

	public Course(String professorName, String courseTitle, int currentCount, int courseGrade, CourseStatus courseStatus)
	{
		this.professorName = professorName;
		this.courseTitle = courseTitle;
		this.currentCount = currentCount;
		this.courseGrade = courseGrade;
		this.maxCourseCount = 10;
		this.courseStatus = courseStatus;
	}

}
