package Course.Controller.DTO.req;

import data.Status.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseReqDto {
	public CourseReqDto() {
	}

	private String professorName;
	private String courseTitle;
	private Integer currentCount;
}
