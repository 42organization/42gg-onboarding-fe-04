package ft.gg.onboarding.entity.student;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentStatus {

    ATTEND("attend"),
    DROP("drop"),
    GRADUATE("graduate");

    private final String value;
}