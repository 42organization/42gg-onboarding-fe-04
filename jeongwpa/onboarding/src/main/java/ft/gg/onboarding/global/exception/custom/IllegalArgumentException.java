package ft.gg.onboarding.global.exception.custom;

import lombok.Getter;

@Getter
public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String message) {
        super(message);
    }
}
