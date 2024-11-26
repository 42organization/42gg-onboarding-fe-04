package com.example.onboarding.data.entity;

import com.example.onboarding.data.Status.StudentStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer birth;

	@Column(name = "current_grade")
	private Integer currentGrade;

	@Column(name = "total_grade")
	private Integer totalGrade;

	@Enumerated(EnumType.STRING)
	@Column(name = "is_graduated")
	private StudentStatus isGraduated; // 학생 상태

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBirth() {
		return birth;
	}

	public void setBirth(Integer birth) {
		this.birth = birth;
	}

	public Integer getCurrentGrade() {
		return currentGrade;
	}

	public void setCurrentGrade(Integer currentGrade) {
		this.currentGrade = currentGrade;
	}

	public StudentStatus getIsGraduated() {
		return isGraduated;
	}

	public void setIsGraduated(StudentStatus isGraduated) {
		this.isGraduated = isGraduated;
	}

	public Integer getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(Integer totalGrade) {
		this.totalGrade = totalGrade;
	}

	// Getters and Setters
}