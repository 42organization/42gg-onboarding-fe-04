package ft.gg.onboarding.global.exception.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }
}
