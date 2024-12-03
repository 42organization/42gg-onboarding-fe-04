package jpabook.onboarding.data.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jpabook.onboarding.data.status.StudentStatus;
import jpabook.onboarding.exception.CustomError;
import jpabook.onboarding.exception.CustomException;
import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

	private static final int MAX_CURRENT_GRADE = 15;
	private static final int MAX_TOTAL_GRADE = 60;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private Set<Sugang> sugangs = new HashSet<>();

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate birth;

	@Column
	private int currentGrade;

	@Column
	private int totalGrade;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private StudentStatus status;

	public Student(final StudentRequestDto request) {
		this.name = request.getName();
		this.birth = request.getBirth();
		this.status = StudentStatus.ENROLLED;
	}

	public void updateStatus(final StudentStatus status) {
		this.status = status;
	}

	public void addCurrentGrade(final int grade) {
		if (this.currentGrade + grade > MAX_CURRENT_GRADE) {
			throw new CustomException(CustomError.BAD_REQUEST);
		}
		this.currentGrade += grade;
	}

	public void addTotalGrade(final int grade) {
		if (this.totalGrade == MAX_TOTAL_GRADE) {
			throw new CustomException(CustomError.BAD_REQUEST);
		}
		this.totalGrade = Integer.min(this.totalGrade + grade, MAX_TOTAL_GRADE);
		if (this.totalGrade == MAX_TOTAL_GRADE) {
			this.status = StudentStatus.GRADUATED;
		}
	}
}