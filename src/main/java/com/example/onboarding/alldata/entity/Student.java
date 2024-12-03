package com.example.onboarding.alldata.entity;

import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.status.StudentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotBlank
	private String studentName;

	@Column
	@NotNull
	@JsonFormat(pattern = "yyyyMMdd")
	private LocalDate studentBirth;

	@Column
	private int currentGrade;

	@Column
	private int totalGrade;

	@Column
	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private StudentStatus studentStatus;

	/*private*/
	private Student(String studentName, LocalDate studentBirth, int currentGrade, int totalGrade,
		StudentStatus studentStatus) {
		this.studentName = studentName;
		this.studentBirth = studentBirth;
		this.currentGrade = currentGrade;
		this.totalGrade = totalGrade;
		this.studentStatus = studentStatus;
	}

	public static Student of(String studentName, LocalDate studentBirth, Integer currentGrade, Integer totalGrade,
		StudentStatus studentStatus) {
		return new Student(studentName, studentBirth, currentGrade != null ? currentGrade : 0,
			totalGrade != null ? totalGrade : 0, studentStatus != null ? studentStatus : StudentStatus.ACTIVE);
	}

	private class StatusManager {
		private void updateStatus(StudentStatus newStatus) {
			if (studentStatus != newStatus) {
				validateStatus();
				studentStatus = newStatus;
			}
		}

		private void validateStatus() {
			if (!isStatusActive())
				throw new CustomException(ErrorCode.STUDENT_NOT_FOUND);
		}

		private boolean isStatusActive() {
			return studentStatus == StudentStatus.ACTIVE && totalGrade <= 60;
		}
	}

	private class GradeManager {
		void addCredit(int credit) {
			validateCreditAdd(credit);
			currentGrade += credit;
			totalGrade += credit;
			if (totalGrade >= 60) {
				statusManager.updateStatus(StudentStatus.GRADUATED);
			}
		}

		void removeCredit(int credit) {
			if (currentGrade >= credit) {
				currentGrade -= credit;
				totalGrade -= credit;
			}
		}

		void validateCreditAdd(int credit) {
			if (currentGrade + credit > 15)
				throw new CustomException(ErrorCode.STUDENT_OVER_GRADE);
			if (totalGrade + credit > 60)
				throw new CustomException(ErrorCode.STUDENT_OVER_TOTALGRADE);
		}
	}

	@Transient
	private final StatusManager statusManager = new StatusManager();
	@Transient
	private final GradeManager gradeManager = new GradeManager();

	public void drop() {
		statusManager.updateStatus(StudentStatus.DROPOUT);
	}

	public boolean canRegister() {
		return statusManager.isStatusActive();
	}

	public void addCredit(int credit) {
		gradeManager.addCredit(credit);
	}

	public void removeCredit(int credit) {
		gradeManager.removeCredit(credit);
	}
}
