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

	private boolean isStatusRegistered() {
		return CourseStatus.REGISTERED == courseStatus;
	}

	private void validate(CourseReqDto req) {
		if (!isStatusRegistered())
			throw new CustomException(ErrorCode.COURSE_NOT_CHANGE);
		int currentCount = req.getCurrentCount() != null ? req.getCurrentCount() : this.currentCount;
		if (currentCount > getMaxCourseCount())
			throw new CustomException(ErrorCode.COURSE_MAX_OVER);
	}

	private void updateStatus(CourseStatus newStatus) {
		if (courseStatus != newStatus) {
			if (!isStatusRegistered())
				throw new CustomException(ErrorCode.COURSE_NOT_CHANGE);
			courseStatus = newStatus;
		}
	}

	public void update(CourseReqDto req) {
		validate(req);
		updateInfo(req);
	}

	private void updateInfo(CourseReqDto req) {
		this.professorName = req.getProfessorName();
		this.courseTitle = req.getCourseTitle();
		if (req.getCurrentCount() != null) {
			this.currentCount = req.getCurrentCount();
		}
		if (req.getCourseGrade() != null) {
			this.courseGrade = req.getCourseGrade();
		}
		if (req.getCourseStatus() != null) {
			updateStatus(req.getCourseStatus());
		}
	}

	public boolean exceedCapacity() {
		return currentCount >= MAX_COURSE_COUNT;
	}

	private void addStudent() {
		if (exceedCapacity())
			throw new CustomException(ErrorCode.COURSE_MAX_OVER);
		currentCount++;
		if (exceedCapacity())
			updateStatus(CourseStatus.COMPLETED);
	}

	private void removeStudent() {
		if (currentCount > 0)
			currentCount--;
	}

	public void delete() {
		updateStatus(CourseStatus.DELETED);
	}

	public void complete() {
		updateStatus(CourseStatus.COMPLETED);
	}

	public void plusCurrentCount() {
		addStudent();
	}

	public void minusCurrentCount() {
		removeStudent();
	}
}
