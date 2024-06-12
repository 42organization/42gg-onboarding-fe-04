package ft.gg.onboarding.dto.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursePageRequestDto {

    private String sort;

    private String order;
}
