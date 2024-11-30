package com.example.onboarding.alldata.repository;

import com.example.onboarding.alldata.entity.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	boolean existsByProfessorNameAndCourseTitleAndIdNot(
		String professorName,
		String courseTitle,
		Integer id
	);
}
