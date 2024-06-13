package gg.onboarding.data.dto.course.response;

import java.time.LocalDateTime;

import gg.onboarding.data.entity.Course;

public class CourseResDto {
	private String name;
	private String professorName;
	private Integer credit;
	private Boolean isTrue;
	private Integer maxStudentCount;
	private Integer currentStudentCount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public CourseResDto(Course course) {
		this.name = course.getName();
		this.professorName = course.getProfessorName();
		this.credit = course.getCredit();
		this.isTrue = course.getIsTrue();
		this.maxStudentCount = course.getMaxStudentCount();
		this.currentStudentCount = course.getCurrentStudentCount();
		this.createdAt = course.getCreatedAt();
		this.updatedAt = course.getUpdatedAt();
	}
}
