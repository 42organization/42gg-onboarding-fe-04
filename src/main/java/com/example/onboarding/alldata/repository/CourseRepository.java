package com.example.onboarding.alldata.repository;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.status.CourseStatus;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	boolean existsByProfessorNameAndCourseTitleAndIdNot(
		String professorName,
		String courseTitle,
		Integer id
	);
	Page<Course> findByCourseStatus(CourseStatus courseStatus, Pageable pageable);
}


//TODO: sssssss