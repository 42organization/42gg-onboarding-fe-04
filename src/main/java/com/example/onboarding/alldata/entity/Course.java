package com.example.onboarding.alldata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.status.CourseStatus;
import com.example.onboarding.course.controller.dto.req.CourseReqDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@Pattern(regexp = "^[가-힣a-zA-Z\\s]+$", message = "교수 이름은 한글과 영문만 가능합니다")
	@Size(max = 50, message = "교수 이름은 50자를 초과할 수 없습니다")
	@NotBlank(message = "교수 이름은 필수입니다")
	private String professorName;

	@Column(name = "course_title", nullable = false)
	@Pattern(regexp = "^[가-힣a-zA-Z\\s]+$", message = "강의 이름은 한글과 영문만 가능합니다")
	@Size(max = 50, message = "강의 이름은 50자를 초과할 수 없습니다")
	@NotBlank(message = "강의명은 필수입니다")
	private String courseTitle;

	@Column(name = "current_count", nullable = false)
	@Max(value = 10, message = "수강신청 인원은 10명을 초과할 수 없습니다")
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

	public void update(CourseReqDto req) {
		validate(req);
		this.professorName = req.getProfessorName();
		this.courseTitle = req.getCourseTitle();
		this.currentCount = req.getCurrentCount();
		this.courseGrade = req.getCourseGrade();
		this.courseStatus = req.getStatus();
	}

	public void validate(CourseReqDto req)
	{
		if (getCourseStatus() != (CourseStatus.REGISTERED))
			throw new CustomException(ErrorCode.COURSE_NOT_CHANGE);
		if (req.getCurrentCount() > getMaxCourseCount())
			throw new CustomException(ErrorCode.COURSE_MAX_OVER);
	}

	public void delete()
	{
		if (getCourseStatus() != CourseStatus.REGISTERED)
			throw new CustomException(ErrorCode.COURSE_NOT_CHANGE);
		this.courseStatus = CourseStatus.DELETED;
	}

	public void complete()
	{
		if (getCourseStatus() != CourseStatus.REGISTERED)
			throw new CustomException(ErrorCode.COURSE_NOT_CHANGE);
		this.courseStatus = CourseStatus.COMPLETED;
	}

	public boolean exceedCapacity()
	{
		return currentCount >= 10;
	}

	public void plusCurrentGrade()
	{
		if (currentCount >= 10)
		{
			throw new IllegalStateException("강의 정원이 초과되었습니다.");
		}
		this.currentCount++;
		if (currentCount >= 10)
			this.courseStatus = CourseStatus.COMPLETED;
	}

	public void minusCurrentGrade()
	{
		if (currentCount > 0)
			this.currentCount--;
	}

}
