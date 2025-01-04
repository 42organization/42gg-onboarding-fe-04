package com.example.onboarding.alldata.validator;

import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.exception.CustomException;
import com.example.onboarding.alldata.exception.ErrorCode;
import com.example.onboarding.alldata.status.StudentStatus;

public class StudentValidator {

	public static void validateSugangOrDrop(StudentStatus status, int totalGrade) {
		if (!isStatusActive(status, totalGrade))
			throw new CustomException(ErrorCode.STUDENT_NOT_FOUND);
	}

	private static boolean isStatusActive(StudentStatus status, int totalGrade) {
		return status == StudentStatus.ACTIVE && totalGrade <= Student.MAX_TATAL_GRADE;
	}
}
