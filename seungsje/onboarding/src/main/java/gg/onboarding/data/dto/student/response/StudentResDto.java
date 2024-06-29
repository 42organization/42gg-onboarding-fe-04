package gg.onboarding.data.dto.student.response;

import java.time.LocalDateTime;

import gg.onboarding.data.entity.Student;
import gg.onboarding.data.entity.custom.StudentStatus;

public class StudentResDto {
	private String name;
	private LocalDateTime birthDate;
	private StudentStatus status;
	private Integer totalCredit;
	private Integer enrolledCredit;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public StudentResDto(Student student) {
		this.name = student.getName();
		this.birthDate = student.getBirthDate();
		this.status = student.getStatus();
		this.totalCredit = student.getTotalCredit();
		this.enrolledCredit = student.getEnrolledCredit();
		this.createdAt = student.getCreatedAt();
		this.updatedAt = student.getUpdatedAt();
	}
}

