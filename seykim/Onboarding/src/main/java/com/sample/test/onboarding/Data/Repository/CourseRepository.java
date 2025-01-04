package com.sample.test.onboarding.Data.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Status.CourseStatus;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findByProfessorNameAndCourseName(String professorName, String courseName);

	Page<Course> findByStatus(CourseStatus status, Pageable pageable);

}
