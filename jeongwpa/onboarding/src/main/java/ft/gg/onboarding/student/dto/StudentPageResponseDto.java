package ft.gg.onboarding.student.dto;

import ft.gg.onboarding.entity.Student;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentPageResponseDto {

    private int page;

    private int size;

    private int total;

    private List<StudentResponseDto> students;

    @Builder
    public StudentPageResponseDto(int page, int size, int total, List<StudentResponseDto> students) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.students = students;
    }

    @Mapper
    public interface MapStruct {

        StudentPageResponseDto.MapStruct INSTANCE = Mappers.getMapper(StudentPageResponseDto.MapStruct.class);

        @Mapping(target = "page", source = "number")
        @Mapping(target = "size", source = "size")
        @Mapping(target = "total", source = "totalElements")
        @Mapping(target = "students", source = "content")
        StudentPageResponseDto toDto(Page<Student> students);
    }
}
