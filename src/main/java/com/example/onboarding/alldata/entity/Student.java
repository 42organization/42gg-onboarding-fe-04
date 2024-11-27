package com.example.onboarding.alldata.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.onboarding.alldata.status.StudentStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
	private String studentName;

	@Column(name="student_birth", nullable = false)
	@NotBlank(message = "학생 생년월일은 필수입니다")
	private int studentBirth;

	@Column(name = "current_grade")
	private int currentGrade;

	@Column(name = "total_grade")
	private int studentTotalGrade;

	@Column(name = "student_status")
	@Enumerated(EnumType.STRING) // 값으로 string을 넣겠다
	@JdbcTypeCode(SqlTypes.VARCHAR) // sql colume 값에 넣을 데이터가 varchar
	private StudentStatus studentStatus; // 학생 상태

	public Student(String studentName, int studentBirth)
	{
		this.studentName = studentName;
		this.studentBirth = studentBirth;
		this.currentGrade = 0;
		this.studentTotalGrade = 0;
		this.studentStatus = StudentStatus.ACTIVE;
	}

	public Student(String studentName, int studentBirth, int currentGrade, int studentTotalGrade, StudentStatus studentStatus)
	{
		this.studentName = studentName;
		this.studentBirth = studentBirth;
		this.currentGrade = currentGrade;
		this.studentTotalGrade = studentTotalGrade;
		this.studentStatus = studentStatus;
	}
}