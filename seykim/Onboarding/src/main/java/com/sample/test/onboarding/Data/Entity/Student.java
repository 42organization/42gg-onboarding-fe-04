package com.sample.test.onboarding.Data.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.sample.test.onboarding.Data.Status.StudentStatus;

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
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDateTime birthDay;

	@Column
	private int currentGrade;

	@Column
	private int totalGrade;

	@Enumerated(EnumType.STRING)
	@Column
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private StudentStatus status;

	public Student(String name, LocalDateTime birthDay) {
		this.name = name;
		this.birthDay = birthDay;
		this.currentGrade = 0;
		this.totalGrade = 0;
		this.status = StudentStatus.ENROLLED;
	}

	public void drop() {
		this.status = StudentStatus.DROPPED;
	}

	public void graduated() {
		this.status = StudentStatus.GRADUATED;
	}

	public void enrollCourse(int grade) {
		if (this.currentGrade + grade > 15) {
			throw new IllegalArgumentException("Exceeds the maximum grade");
		}
		this.currentGrade += grade;
	}

	public void cancelCourse(int grade) {
		this.currentGrade -= grade;
	}

	public void completeCourse(int grade) {
		if (this.totalGrade > 60) {
			throw new IllegalArgumentException("Already graduated");
		} else if (this.totalGrade + grade >= 60) {
			this.totalGrade = 60;
			this.graduated();
		} else {
			this.totalGrade += grade;
		}
	}
}