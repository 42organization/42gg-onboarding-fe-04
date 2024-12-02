package jpabook.onboarding.sugang.controller.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SugangRequestDto {
	@NotBlank(message = "이름은 필수입니다.")
	private final String name;

	@NotNull(message = "생일은 필수입니다.")
	private final LocalDate birth;
}
