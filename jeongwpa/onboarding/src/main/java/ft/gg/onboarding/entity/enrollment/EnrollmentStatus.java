package ft.gg.onboarding.entity.enrollment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnrollmentStatus {

    ENROLL("enroll"),
    CANCEL("drop"),
    SUCCESS("success");

    private final String value;
}
