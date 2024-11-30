package com.example.onboarding.alldata.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.status.StudentStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name ="student_name", nullable = false)
	@NotBlank(message = "학생 이름은 필수입니다")
	@Pattern(regexp = "^[가-힣a-zA-Z\\s]+$", message = "학생 이름은 한글과 영문만 가능합니다")
	@Size(max = 50, message = "학생 이름은 50자를 초과할 수 없습니다")
	private String studentName;

	@Column(name="student_birth", nullable = false)
	@NotNull(message = "학생 생년월일은 필수입니다")
	private Integer studentBirth;

	@Column(name = "current_grade")
	private int currentGrade;

	@Column(name = "total_grade")
	private int totalGrade;

	@Column(name = "student_status")
	@Enumerated(EnumType.STRING) // 값으로 string을 넣겠다
	@JdbcTypeCode(SqlTypes.VARCHAR) // sql colume 값에 넣을 데이터가 varchar
	private StudentStatus studentStatus; // 학생 상태

	public Student(String studentName, Integer studentBirth)
	{
		this.studentName = studentName;
		this.studentBirth = studentBirth;
		this.currentGrade = 0;
		this.totalGrade = 0;
		this.studentStatus = StudentStatus.ACTIVE;
	}

	public Student(String studentName, Integer studentBirth,
		int currentGrade, int totalGrade, StudentStatus studentStatus)
	{
		this.studentName = studentName;
		this.studentBirth = studentBirth;
		this.currentGrade = currentGrade;
		this.totalGrade = totalGrade;
		this.studentStatus = studentStatus;
	}

	public void drop()
	{
		if (getStudentStatus() != StudentStatus.ACTIVE)
			throw new CustomException(ErrorCode.STUDENT_NOT_CHANGE);
		this.studentStatus = StudentStatus.GRADUATED;
	}
}