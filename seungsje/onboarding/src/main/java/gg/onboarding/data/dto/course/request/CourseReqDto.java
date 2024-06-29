package gg.onboarding.data.dto.course.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CourseReqDto {
	@NotNull
	@Size(min = 1, max = 20)
	private String name;
	@NotNull
	@Size(min = 1, max = 20)
	private String professorName;
	@NotNull
	@Min(1)
	@Max(3)
	private Integer credit;
	@NotNull
	@Min(1)
	@Max(30)
	private Integer maxStudentCount;
}
