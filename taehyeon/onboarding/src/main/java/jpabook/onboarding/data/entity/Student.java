package jpabook.onboarding.data.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import jpabook.onboarding.student.controller.dto.request.StudentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Sugang> sugangs = new ArrayList<>();

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
}