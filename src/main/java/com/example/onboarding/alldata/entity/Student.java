package com.example.onboarding.alldata.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.status.StudentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "student_name", nullable = false, length = 50)
	@NotBlank(message = "학생 이름은 필수입니다") // 데이터베이스에 접근
	@Pattern(regexp = "^[가-힣a-zA-Z\\s]+$", message = "학생 이름은 한글과 영문만 가능합니다")    // TODO: 수정!
	private String studentName;

	@Column(name = "student_birth", nullable = false)
	@NotNull(message = "학생 생년월일은 필수입니다") // TODO: Integer -> Date로 바꾸기
	private Integer studentBirth;

	@Column(name = "current_grade")
	private int currentGrade;

	@Column(name = "total_grade")
	private int totalGrade;

	@Column(name = "student_status")
	@Enumerated(EnumType.STRING) // 값으로 string을 넣겠다
	@JdbcTypeCode(SqlTypes.VARCHAR) // sql colume 값에 넣을 데이터가 varchar
	private StudentStatus studentStatus; // 학생 상태

	/*private*/
	private Student(String studentName, Integer studentBirth,
		Integer currentGrade, Integer totalGrade, StudentStatus studentStatus) {
		this.studentName = studentName;
		this.studentBirth = studentBirth;
		this.currentGrade = currentGrade;
		this.totalGrade = totalGrade;
		this.studentStatus = studentStatus;
	}

	/**
	 * new Student(sn, sb);
	 * Student.of(sn,sb);
	 */
	public static Student of(
		String studentName,
		Integer studentBirth,
		Integer currentGrade,
		Integer totalGrade,
		StudentStatus studentStatus
	) {
		return new Student(
			studentName,
			studentBirth,
			currentGrade != null ? currentGrade : 0,
			totalGrade != null ? totalGrade : 0,
			studentStatus != null ? studentStatus : StudentStatus.ACTIVE
		);
	}

	public static Student of(String studentName, Integer studentBirth) {
		return Student.of(studentName, studentBirth, null, null, null);
	}

	public void drop() {
		if (getStudentStatus() != StudentStatus.ACTIVE)
			throw new CustomException(ErrorCode.STUDENT_NOT_CHANGE);
		this.studentStatus = StudentStatus.DROPOUT;
	}

	public boolean isGraduated() {
		return getTotalGrade() >= 60;
	}

	public void getGradeCredit(int credit) {
		if (this.currentGrade + credit > 15)
			throw new IllegalStateException("한 학기 최대 수강가능 학점을 초과했습니다");
		if (this.currentGrade + this.totalGrade + credit > 60)
			throw new IllegalStateException("총 이수 가능 학점을 초과했습니다");
		this.currentGrade += credit;
		this.totalGrade += credit;
		if (totalGrade == 60)
			this.studentStatus = StudentStatus.GRADUATED;
	}

	public void removeGradeCredit(int credit) {
		if (this.currentGrade >= credit)
			currentGrade -= credit;
	}
}
