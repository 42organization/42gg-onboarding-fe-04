package ft.gg.onboarding.student.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentRequestDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Builder
    public StudentRequestDto(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }


}
