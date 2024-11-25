package data.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import data.Status.CourseStatus;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "professor_name", nullable = false)
	private String professorName;

	@Column(name = "course_title", nullable = false)
	private String courseTitle;

	@Column(name = "current_count", nullable = false)
	private Integer currentCount;

	@Column(name = "max_count")
	private Integer maxCount;

	@Column
	private Integer grade;

	@Enumerated(EnumType.STRING)
	@Column(name = "is_active")
	private CourseStatus isActive; // 강의 상태

	// public Course(String professorName, String courseTitle, Integer currentCount, Integer maxCount, Integer grade,
	// 	CourseStatus isActive) {
	// 	this.professorName = professorName;
	// 	this.courseTitle = courseTitle;
	// 	this.currentCount = 0;
	// 	this.maxCount = 10;
	// 	this.grade = 0;
	// 	this.isActive = isActive;
	// }
	//
	// public Course() {
	// 	this.professorName = professorName;
	// 	this.courseTitle = courseTitle;
	// 	this.currentCount = 0;
	// 	this.maxCount = 10;
	// 	this.grade = 0;
	// 	this.isActive = isActive;
	// }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public Integer getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public CourseStatus getIsActive() {
		return isActive;
	}

	public void setIsActive(CourseStatus isActive) {
		this.isActive = isActive;
	}
}
