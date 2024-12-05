package com.example.onboarding.alldata.entity;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.status.CourseStatus;
import com.example.onboarding.course.controller.dto.req.CourseReqDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Course {
	private static final int MAX_COURSE_COUNT = 10;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotBlank
	private String professorName;

	@Column
	@NotBlank
	private String courseTitle;

	@Column
	private int currentCount;

	@Column
	private int courseGrade;

	@Column
	private int maxCourseCount;

	@Enumerated(EnumType.STRING)
	@Column
	private CourseStatus courseStatus;

	private Course(String professorName, String courseTitle, int currentCount, int courseGrade,
		CourseStatus courseStatus) {
		this.professorName = professorName;
		this.courseTitle = courseTitle;
		this.currentCount = currentCount;
		this.courseGrade = courseGrade;
		this.maxCourseCount = MAX_COURSE_COUNT;
		this.courseStatus = courseStatus;
	}

	public static Course of(String professorName, String courseTitle, Integer currentCount, Integer courseGrade,
		CourseStatus courseStatus) {
		int resolvedCount = currentCount;
		if (currentCount == null) {
			resolvedCount = 0;
		}
		int resolvedGrade = courseGrade;
		if (courseGrade == null) {
			resolvedGrade = 3;
		}
		CourseStatus resolvedStatus = courseStatus;
		if (courseStatus == null) {
			resolvedStatus = CourseStatus.REGISTERED;
		}
		return new Course(professorName, courseTitle, resolvedCount, resolvedGrade, resolvedStatus);
	}

	private class StatusManager {
		private void updateStatus(CourseStatus newStatus) {
			if (courseStatus != newStatus) {
				validateStatus();
				courseStatus = newStatus;
			}
		}

		private void validateStatus() {
			if (!isStatusRegistered())
				throw new CustomException(ErrorCode.COURSE_NOT_CHANGE);
		}

		private boolean isStatusRegistered() {
			return CourseStatus.REGISTERED == courseStatus;
		}
	}

	private class EnrollmentManager {
		void addStudent() {
			if (exceedCapacity())
				throw new CustomException(ErrorCode.COURSE_MAX_OVER);
			currentCount++;
			if (exceedCapacity())
				new StatusManager().updateStatus(CourseStatus.COMPLETED);
		}

		void removeStudent() {
			if (currentCount > 0)
				currentCount--;
		}

		boolean exceedCapacity() {
			return currentCount >= MAX_COURSE_COUNT;
		}
	}

	@Transient
	private final StatusManager statusManager = new StatusManager();
	@Transient
	private final EnrollmentManager enrollmentManager = new EnrollmentManager();

	public void update(CourseReqDto req) {
		validate(req);
		updateInfo(req);
	}

	public void validate(CourseReqDto req) {
		if (!statusManager.isStatusRegistered())
			throw new CustomException(ErrorCode.COURSE_NOT_CHANGE);
		int currentCount = req.getCurrentCount() != null ? req.getCurrentCount() : this.currentCount;
		if (currentCount > getMaxCourseCount())
			throw new CustomException(ErrorCode.COURSE_MAX_OVER);
	}

	public void updateInfo(CourseReqDto req) {
		this.professorName = req.getProfessorName();
		this.courseTitle = req.getCourseTitle();
		if (req.getCurrentCount() != null) {
			this.currentCount = req.getCurrentCount();
		}
		if (req.getCourseGrade() != null) {
			this.courseGrade = req.getCourseGrade();
		}
		if (req.getCourseStatus() != null) {
			statusManager.updateStatus(req.getCourseStatus());
		}
	}

	public void delete() {
		statusManager.updateStatus(CourseStatus.DELETED);
	}

	public void complete() {
		statusManager.updateStatus(CourseStatus.COMPLETED);
	}

	public boolean exceedCapacity() {
		return enrollmentManager.exceedCapacity();
	}

	public void plusCurrentCount() {
		enrollmentManager.addStudent();
	}

	public void minusCurrentCount() {
		enrollmentManager.removeStudent();
	}
}
