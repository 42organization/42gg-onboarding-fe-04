package com.example.onboarding.sugang.controller.dto.res;

import com.example.onboarding.alldata.status.CourseStatus;
import com.example.onboarding.alldata.status.SugangStatus;
import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Sugang;

import lombok.Getter;

@Getter
public class SugangPageResDto {
	// sugang 관련 정보
	private Integer sugangId;
	private SugangStatus sugangStatus;

	// 페이지네이션의 course 정보
	private Integer courseId;
	private String courseTitle;
	private String professorName;
	private Integer currentCount;
	private Integer maxCount;
	private Integer grade;
	private CourseStatus courseStatus;
}

