package com.example.onboarding.alldata.entity;

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
		this.student = student;
		this.course = course;
		this.status = SugangStatus.REQUESTING;
	}

	public void updateSugangStatus(SugangStatus status)
	{
		this.status = status;
	}
}