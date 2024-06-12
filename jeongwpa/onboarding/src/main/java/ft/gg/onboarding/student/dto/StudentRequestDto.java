package ft.gg.onboarding.student.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentRequestDto {

    private String name;

    private LocalDate birthDate;

    @Builder
    public StudentRequestDto(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }


}
