package com.example.onboarding.data.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.example.onboarding.data.Status.CourseStatus;

import jdk.jfr.Registered;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "professor_name", nullable = false)
	private String professorName;

	@Column(name = "course_title", nullable = false)
	private String courseTitle;

	@Column(name = "current_count", nullable = false)
	private Integer currentCount;

	@Setter
	@Column
	private Integer grade;

	@Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "is_active")
	private CourseStatus isActive; // 강의 상태

	@Column(name = "max_count")
	private Integer maxCount;

	public Course(String professorName, String courseTitle, Integer currentCount)
	{
		this.professorName = professorName;
		this.courseTitle = courseTitle;
		this.currentCount = currentCount;
		this.grade = 3;
		this.isActive = CourseStatus.REGISTERED;
		this.maxCount = 10;
	}
}
