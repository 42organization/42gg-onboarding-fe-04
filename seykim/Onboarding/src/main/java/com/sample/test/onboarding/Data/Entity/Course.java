package com.sample.test.onboarding.Data.Entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.sample.test.onboarding.Data.Status.CourseStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String professorName;

	@Column(nullable = false)
	private String courseName;

	@Column(nullable = false)
	private int currentCount;

	@Column
	private int grade;

	@Column
	private static int maxCount = 10;

	@Enumerated(EnumType.STRING)
	@Column
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private CourseStatus status;

	public Course(String professorName, String courseName) {
		this.professorName = professorName;
		this.courseName = courseName;
		this.currentCount = 0;
		this.grade = 0;
		this.status = CourseStatus.ACTIVE;
	}

	public Course(String professorName, String courseName, int currentCount, int grade, CourseStatus status) {
		this.professorName = professorName;
		this.courseName = courseName;
		this.currentCount = currentCount;
		this.grade = grade;
		this.status = status;
	}

	public void delete() {
		if (this.status == CourseStatus.DELETE) {
			throw new IllegalArgumentException("이미 삭제된 강의입니다.");
		}
		this.status = CourseStatus.DELETE;
	}

	public void complete() {
		if (this.status == CourseStatus.COMPLETED) {
			throw new IllegalArgumentException("이미 완료된 강의입니다.");
		}
		this.status = CourseStatus.COMPLETED;
	}
}
