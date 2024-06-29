package gg.onboarding.data.dto.student.request;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class StudentReqDto {
	private String name;
	private LocalDateTime birthDate;
}
