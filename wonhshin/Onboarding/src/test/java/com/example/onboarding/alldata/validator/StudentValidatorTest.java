package com.example.onboarding.alldata.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.status.StudentStatus;

class StudentValidatorTest {

	@Test
	public void testValidateSugangOrDrop_WithStatusDrop_ThrowCustomException() {
		StudentStatus status = StudentStatus.DROPOUT;
		int totalGrade = 1;

		Assertions.assertThatThrownBy(() ->
			StudentValidator.validateSugangOrDrop(status, totalGrade)
		).isInstanceOf(CustomException.class);
	}
}
