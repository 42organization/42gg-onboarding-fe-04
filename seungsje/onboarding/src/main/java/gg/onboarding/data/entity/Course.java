package gg.onboarding.data.entity;

import gg.onboarding.data.dto.course.request.CourseReqDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 20, nullable = false)
	private String name;

	@Column(name = "professor_name", length = 20, nullable = false)
	private String professorName;

	@Column(name = "credit", nullable = false)
	private Integer credit;

	@Column(name = "is_true", nullable = false)
	private Boolean isTrue;

	@Column(name = "max_student_count", nullable = false)
	private Integer maxStudentCount;

	@Column(name = "current_student_count", nullable = false)
	private Integer currentStudentCount;

	public Course(CourseReqDto courseReqDto) {
		this.name = courseReqDto.getName();
		this.professorName = courseReqDto.getProfessorName();
		this.credit = courseReqDto.getCredit();
		this.maxStudentCount = courseReqDto.getMaxStudentCount();
		this.currentStudentCount = 0;
		this.isTrue = true;
	}

	public void patch(CourseReqDto courseReqDto) {
		this.name = courseReqDto.getName();
		this.professorName = courseReqDto.getProfessorName();
		this.credit = courseReqDto.getCredit();
		this.maxStudentCount = courseReqDto.getMaxStudentCount();
	}

	public void delete() {
		this.isTrue = false;
	}

	public void finish() {
		this.isTrue = false;
	}
}
