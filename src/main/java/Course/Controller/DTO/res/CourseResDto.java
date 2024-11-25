package Course.Controller.DTO.res;

import data.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResDto {
	private String status;
	private Integer id;
	private String professorName;
	private String courseTitle;

	public CourseResDto(Course course, String status) {
		this.status = status;
		this.id = course.getId();
		this.professorName = course.getProfessorName();
		this.courseTitle = course.getCourseTitle();
	}

	public CourseResDto(String status) {
		this.status = status;
	}
}