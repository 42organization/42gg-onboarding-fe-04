package gg.onboarding.data.entity;

import java.time.LocalDateTime;

import gg.onboarding.data.entity.custom.StudentStatus;
import gg.onboarding.data.dto.student.request.StudentReqDto;
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
public class Student extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 20, nullable = false)
	private String name;

	@Column(name = "birth_date", nullable = false)
	private LocalDateTime birthDate;

	@Column(name = "status", length = 10, nullable = false)
	private StudentStatus status;

	@Column(name = "total_credit", nullable = false)
	private Integer totalCredit;

	@Column(name = "enrolled_credit", nullable = false)
	private Integer enrolledCredit;

	public static Student toEntity(StudentReqDto studentReqDto) {
		Student student = new Student();
		student.name = studentReqDto.getName();
		student.birthDate = studentReqDto.getBirthDate();
		student.status = StudentStatus.ATTEND;
		student.totalCredit = 0;
		student.enrolledCredit = 0;
		return student;
	}

	public void drop() {
		this.status = StudentStatus.DROP;
	}

	public void patchTotalCredit(Integer credit) {
		this.totalCredit += credit;
	}

	public void patchPlusEnrolledCredit(Integer credit) {
		if (this.enrolledCredit + credit > this.totalCredit) {
			throw new IllegalArgumentException("Enrolled credit is over total credit");
		}
		this.enrolledCredit += credit;
	}

	public void patchMinusEnrolledCredit(Integer credit) {
		this.enrolledCredit -= credit;
	}
}
