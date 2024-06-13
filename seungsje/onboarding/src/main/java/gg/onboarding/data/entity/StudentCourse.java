package gg.onboarding.data.entity;

import gg.onboarding.data.entity.custom.StudentCourseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StudentCourse extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@Column(name = "status", length = 10, nullable = false)
	private StudentCourseStatus status;

	public static StudentCourse toEntity(Student student, Course course) {
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.student = student;
		studentCourse.course = course;
		studentCourse.status = StudentCourseStatus.ENROLL;
		return studentCourse;
	}

	public void patchIsDeleted() {
		this.status = StudentCourseStatus.CANCEL;
	}

	public void patchIsFinish() {
		this.status = StudentCourseStatus.FINISH;
	}
}
