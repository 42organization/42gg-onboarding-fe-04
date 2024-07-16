package ft.gg.onboarding.dto.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public StudentPageRequestDto(int page, int size, String sort, String order) {
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.order = order;
    }
}
