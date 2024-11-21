package com.sample.test.onboarding.Data.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Status.CourseStatus;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findByProfessorNameAndCourseName(String professorName, String courseName);

	Page<Course> findByStatusPage(CourseStatus status, Pageable pageable);

	// Course findByCourseId(Long courseId);
}
