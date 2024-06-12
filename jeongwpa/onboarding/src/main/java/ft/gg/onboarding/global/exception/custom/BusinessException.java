package ft.gg.onboarding.global.exception.custom;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
