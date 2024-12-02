package com.example.onboarding.alldata.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.status.CourseStatus;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	boolean existsByProfessorNameAndCourseTitleAndIdNot(
		String professorName,
		String courseTitle,
		Integer id
	);

	Page<Course> findByCourseStatus(CourseStatus courseStatus, Pageable pageable);
}
