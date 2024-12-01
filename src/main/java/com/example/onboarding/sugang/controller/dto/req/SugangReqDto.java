package com.example.onboarding.sugang.controller.dto.req;

import com.example.onboarding.alldata.entity.Sugang;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SugangReqDto {
	@NotBlank
	private String studentName;
	@NotNull
	private Integer studentBirth;
}
