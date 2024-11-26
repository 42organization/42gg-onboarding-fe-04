package jpabook.onboarding.data.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.request.CourseUpdateRequestDto;
import jpabook.onboarding.data.status.CourseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

	private static final int MAX_COUNT = 10;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Long id;

	@Column(nullable = false)
	private String professorName;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int count;

	@Column
	private int grade;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private CourseStatus status;

	public Course(final CourseRequestDto request) {
		this.professorName = request.getProfessorName();
		this.name = request.getName();
		this.count = request.getCount();
		this.grade = 3;
		this.status = CourseStatus.REGISTERED;
	}

	public Course(final CourseUpdateRequestDto request) {
		this.professorName = request.getProfessorName();
		this.name = request.getName();
		this.count = 0;
		this.grade = request.getGrade();
		this.status = request.getStatus();
	}

	public void updateStatus(final CourseStatus status) {
		this.status = status;
	}

	public void update(final CourseUpdateRequestDto request) {
		this.name = request.getName();
		this.grade = request.getGrade();
		this.professorName = request.getProfessorName();
		this.status = request.getStatus();
	}
}