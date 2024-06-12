package ft.gg.onboarding.student.dto;

import ft.gg.onboarding.entity.Student;
import jakarta.validation.constraints.NotNull;
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
    private String name;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
        @Mapping(target = "status", defaultValue = "StudentStatus.ATTEND")
        @Mapping(target = "totalCredit", defaultValue = "0")
        @Mapping(target = "enrolledCredit", defaultValue = "0")
        Student toEntity(StudentCreateDto stud1entCreateDto);
    }
}
