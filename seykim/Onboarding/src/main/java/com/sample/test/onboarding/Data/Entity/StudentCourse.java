package com.sample.test.onboarding.Data.Entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.sample.test.onboarding.Data.Status.StudentCourseStatus;
import com.sample.test.onboarding.Data.Status.StudentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class StudentCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	@Enumerated(EnumType.STRING)
	@Column
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private StudentCourseStatus status;

	public StudentCourse(Student student, Course course, StudentCourseStatus status) {
		this.student = student;
		this.course = course;
		this.status = status;
	}

	public void cancel() {
		if (this.status == StudentCourseStatus.COMPLETED || this.status == StudentCourseStatus.CANCEL) {
			throw new IllegalArgumentException("Already completed or canceled");
		}
		if (this.student.getStatus() == StudentStatus.DROPPED) {
			throw new IllegalArgumentException("Already Student dropped");
		}
		this.status = StudentCourseStatus.CANCEL;
	}

	public void completeStudentCourse() {
		if (this.status == StudentCourseStatus.COMPLETED || this.status == StudentCourseStatus.CANCEL) {
			throw new IllegalArgumentException("Already completed or canceled");
		}
		if (this.student.getStatus() == StudentStatus.DROPPED) {
			throw new IllegalArgumentException("Already Student dropped");
		}
		this.status = StudentCourseStatus.COMPLETED;
	}
}
