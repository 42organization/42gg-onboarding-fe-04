package ft.gg.onboarding.dto.course;

import ft.gg.onboarding.entity.course.Course;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseCreateDto {

    private String name;

    private String professorName;

    private int credit;

    private int maxStudentCount;

    @Builder
    public CourseCreateDto(String name, String professorName, int credit, int maxStudentCount) {
        this.name = name;
        this.professorName = professorName;
        this.credit = credit;
        this.maxStudentCount = maxStudentCount;
    }

    @Mapper
    public interface MapStruct {

        CourseCreateDto.MapStruct INSTANCE = Mappers.getMapper(CourseCreateDto.MapStruct.class);

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "enrollments", ignore = true)
        @Mapping(target = "currentStudentCount", defaultValue = "0")
        @Mapping(target = "isTrue", defaultValue = "true")
        Course toEntity(CourseCreateDto courseCreateDto);
    }
}
