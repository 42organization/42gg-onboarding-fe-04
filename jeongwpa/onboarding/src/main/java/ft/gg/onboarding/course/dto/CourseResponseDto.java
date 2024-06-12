package ft.gg.onboarding.course.dto;

import ft.gg.onboarding.entity.Course;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseResponseDto {

    private String name;

    private String professorName;

    private int credit;

    private boolean isTrue;

    private int maxStudentCount;

    private int currentStudentCount;

    private LocalDateTime createdBy;

    private LocalDateTime updatedBy;

    @Builder
    public CourseResponseDto(String name, String professorName, int credit, boolean isTrue, int maxStudentCount, int currentStudentCount, LocalDateTime createdBy, LocalDateTime updatedBy) {
        this.name = name;
        this.professorName = professorName;
        this.credit = credit;
        this.isTrue = isTrue;
        this.maxStudentCount = maxStudentCount;
        this.currentStudentCount = currentStudentCount;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    @Mapper
    public interface MapStruct {

        CourseResponseDto.MapStruct INSTANCE = Mappers.getMapper(CourseResponseDto.MapStruct.class);

        CourseResponseDto toDto(Course course);
    }
}
