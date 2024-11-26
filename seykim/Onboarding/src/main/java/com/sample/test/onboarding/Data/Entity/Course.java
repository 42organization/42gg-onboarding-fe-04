package com.sample.test.onboarding.Data.Entity;

import java.util.Optional;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.sample.test.onboarding.Data.Exception.CustomException;
import com.sample.test.onboarding.Data.Exception.ErrorResponse;
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
			throw new CustomException(ErrorResponse.COURSE_ALREADY_COMPLETED_OR_DELETED);
		}
		this.status = CourseStatus.DELETE;
	}

	public void complete() {
		if (this.status == CourseStatus.COMPLETED) {
			throw new CustomException(ErrorResponse.COURSE_ALREADY_COMPLETED_OR_DELETED);
		}
		this.status = CourseStatus.COMPLETED;
	}

	public void update(String professorName, String courseName, Optional<Integer> currentCount, Optional<Integer> grade,
		Optional<CourseStatus> status) {
		this.professorName = professorName;
		this.courseName = courseName;

		if (currentCount.isEmpty()) {
			this.currentCount = 0;
		} else if (currentCount.get() > maxCount) {
			throw new CustomException(ErrorResponse.COURSE_MAX_COUNT);
		} else if (currentCount.get() < 0) {
			throw new CustomException(ErrorResponse.BAD_REQUEST);
		} else {
			this.currentCount = currentCount.get();
		}

		if (grade.isEmpty()) {
			this.grade = 0;
		} else if (grade.get() < 0) {
			throw new CustomException(ErrorResponse.BAD_REQUEST);
		} else {
			this.grade = grade.get();
		}

		status.ifPresent(courseStatus -> this.status = courseStatus);
	}

	public void enroll() {
		if (this.status == CourseStatus.COMPLETED) {
			throw new CustomException(ErrorResponse.COURSE_ALREADY_COMPLETED_OR_DELETED);
		} else if (this.currentCount + 1 >= maxCount) {
			throw new CustomException(ErrorResponse.COURSE_MAX_COUNT);
		}
		this.currentCount++;
	}

	public void cancel() {
		if (this.currentCount <= 0) {
			throw new CustomException(ErrorResponse.BAD_REQUEST);
		}
		this.currentCount--;
	}
}
