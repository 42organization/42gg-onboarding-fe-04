package com.example.onboarding.alldata.entity;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.status.SugangStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Sugang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn
	private Student student;

	@ManyToOne
	@JoinColumn
	private Course course;

	@Column
	@Enumerated(EnumType.STRING)
	private SugangStatus status;

	private class StatusManager {
		private void updateStatus(SugangStatus newStatus) {
			if (status == SugangStatus.CANCELED) {
				throw new CustomException(ErrorCode.COURSE_NOT_CHANGE);
			}
			status = newStatus;
		}

		private boolean isEnrolled() {
			return status == SugangStatus.ENROLLED;
		}
	}

	private class EnrollmentValidator {
		void validateEnrollment() {
			validStudentStatus();
			validCourseStatus();
		}

		private void validStudentStatus() {
			if (!(student.canRegister()))
				throw new CustomException(ErrorCode.SUGANG_NOT_REGISTERD);
		}

		private void validCourseStatus() {
			if (course.exceedCapacity())
				throw new CustomException(ErrorCode.SUGANG_NOT_REGISTERD);
		}
	}

	@Transient
	private final StatusManager statusManager = new StatusManager();
	@Transient
	private final EnrollmentValidator enrollmentValidator = new EnrollmentValidator();

	private Sugang(Student student, Course course) {
		this.status = SugangStatus.REQUESTING;
		this.student = student;
		this.course = course;
		enrollmentValidator.validateEnrollment();
		this.status = SugangStatus.ENROLLED;
		processEnrollment();
	}

	public static Sugang of(Student student, Course course) {
		return new Sugang(student, course);
	}

	private void processEnrollment() {
		student.addCredit(course.getCourseGrade());
		course.plusCurrentCount();
		this.status = SugangStatus.ENROLLED;
	}

	public void cancel() {
		if (!statusManager.isEnrolled())
			throw new CustomException(ErrorCode.SUGANG_NOT_REGISTERD);
		student.removeCredit(course.getCourseGrade());
		course.minusCurrentCount();
		this.status = SugangStatus.CANCELED;
	}

}