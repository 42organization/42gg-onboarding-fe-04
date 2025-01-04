package com.example.onboarding.alldata.entity;

import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.status.StudentStatus;
import com.example.onboarding.alldata.validator.StudentValidator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Student {
	public static final int MAX_TATAL_GRADE = 60;
	private static final int MAX_SEMESTER_GRADE = 15;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotBlank
	private String studentName;

	@Column
	@NotNull
	// @JsonFormat(pattern = "yyyyMMdd") // TODO: 패턴말고 exceptionHandler로 오류잡기
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
		int resolvedCurrentGrade = currentGrade;
		if (currentGrade == null) {
			resolvedCurrentGrade = 0;
		}

		int resolvedTotalGrade = totalGrade;
		if (totalGrade == null) {
			resolvedTotalGrade = 0;
		}

		StudentStatus resolvedStatus = studentStatus;
		if (studentStatus == null) {
			resolvedStatus = StudentStatus.ACTIVE;
		}

		return new Student(studentName, studentBirth, resolvedCurrentGrade, resolvedTotalGrade, resolvedStatus);
	}

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
		return studentStatus == StudentStatus.ACTIVE && totalGrade <= MAX_TATAL_GRADE;
	}

	private void add(int credit) {
		validateCreditAdd(credit);
		currentGrade += credit;
		totalGrade += credit;
		if (totalGrade >= MAX_TATAL_GRADE) {
			updateStatus(StudentStatus.GRADUATED);
		}
	}

	private void remove(int credit) {
		if (currentGrade >= credit) {
			currentGrade -= credit;
			totalGrade -= credit;
		}
	}

	private void validateCreditAdd(int credit) {
		if (currentGrade + credit > MAX_SEMESTER_GRADE)
			throw new CustomException(ErrorCode.STUDENT_OVER_GRADE);
		if (totalGrade + credit > MAX_TATAL_GRADE)
			throw new CustomException(ErrorCode.STUDENT_OVER_TOTALGRADE);
	}

	public void drop() {
		StudentValidator.validateSugangOrDrop(studentStatus, totalGrade);
		updateStatus(StudentStatus.DROPOUT);
	}

	public boolean canRegister() {
		return isStatusActive();
	}

	public void addCredit(int credit) {
		add(credit);
	}

	public void removeCredit(int credit) {
		remove(credit);
	}
}
