package ft.gg.onboarding.course.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseUpdateDto {

    private String name;

    private String professorName;

    private int credit;

    private int maxStudentCount;

    @Builder
    public CourseUpdateDto(String name, String professorName, int credit, int maxStudentCount) {
        this.name = name;
        this.professorName = professorName;
        this.credit = credit;
        this.maxStudentCount = maxStudentCount;
    }
}
