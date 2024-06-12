package ft.gg.onboarding.student.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPageRequestDto {

    @NotNull
    @Min(1)
    private int page;

    @NotNull
    @Min(1)
    private int size;

    @NotNull
    private String sort;

    @NotNull
    private String order;
}
