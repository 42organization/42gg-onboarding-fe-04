package ft.gg.onboarding.dto.student;

import ft.gg.onboarding.entity.student.Student;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentCreateDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    private LocalDate birthDate;

    @Builder
    public StudentCreateDto(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Mapper
    public interface MapStruct {

        StudentCreateDto.MapStruct INSTANCE = Mappers.getMapper(StudentCreateDto.MapStruct.class);

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "enrollments", ignore = true)
        @Mapping(target = "status", constant = "ATTEND")
        @Mapping(target = "totalCredit", constant = "0")
        @Mapping(target = "enrolledCredit", constant = "0")
        Student toEntity(StudentCreateDto studentCreateDto);
    }
}
