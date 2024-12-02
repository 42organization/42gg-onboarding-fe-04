package com.example.onboarding.sugang.controller.dto.res;

import java.util.List;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Sugang;
import com.example.onboarding.alldata.status.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SugangResDto {
	private String studentName;
	private Integer studentBirth;
	private int currentGrade;
	private int totalGrade;
	private StudentStatus studentStatus;
	private List<Course> courses;
}

