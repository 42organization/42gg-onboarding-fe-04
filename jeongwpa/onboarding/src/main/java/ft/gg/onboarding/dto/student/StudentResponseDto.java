package ft.gg.onboarding.dto.student;

import ft.gg.onboarding.entity.student.Student;
import ft.gg.onboarding.entity.student.StudentStatus;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentResponseDto {

    private String name;

    private String birthDate;

    private StudentStatus status;

    private int totalCredit;

    private int enrolledCredit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public StudentResponseDto(String name, String birthDate, StudentStatus status, int totalCredit, int enrolledCredit, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.birthDate = birthDate;
        this.status = status;
        this.totalCredit = totalCredit;
        this.enrolledCredit = enrolledCredit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Mapper
    public interface MapStruct {

        StudentResponseDto.MapStruct INSTANCE = Mappers.getMapper(StudentResponseDto.MapStruct.class);

        StudentResponseDto toDto(Student student);
    }
}
