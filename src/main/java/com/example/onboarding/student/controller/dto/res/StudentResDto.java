package com.example.onboarding.student.controller.dto.res;

import com.example.onboarding.alldata.status.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentResDto {
	private String studentName;
	private Integer studentBirth;
	private Integer currentGrade;
	private Integer totalGrade;
	private StudentStatus studentStatus;

}


/*
궁금한 점 1)
- MessageResDTO처럼 세분화되게 쓰는 게 좋을 지?
public class StudentMessageResDto {
	private Integer status;
	private String msg;
}
 */