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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_course")
@Getter
@NoArgsConstructor
public class Sugang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SugangStatus status;


	public Sugang(Student student, Course course)
	{
		this.status = SugangStatus.REQUESTING;
		validEnroll(student, course);
		this.student = student;
		this.course = course;
		this.status = SugangStatus.ENROLLED;
	}

	public void updateSugangStatus(SugangStatus status)
	{

		if (this.status == SugangStatus.CANCELED)
			throw new CustomException(ErrorCode.SUGANG_NOT_FOUND);
		this.status = status;
	}

	private void validEnroll(Student student, Course course)
	{
		if (student.isGraduated())
			throw new CustomException(ErrorCode.SUGANG_NOT_REGISTERD);
		if (course.exceedCapacity())
			throw new CustomException(ErrorCode.SUGANG_NOT_REGISTERD);
		student.getGradeCredit(course.getCourseGrade());
		course.plusCurrentGrade();
	}
}