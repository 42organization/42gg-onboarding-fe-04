package ft.gg.onboarding.global.exception.custom;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
