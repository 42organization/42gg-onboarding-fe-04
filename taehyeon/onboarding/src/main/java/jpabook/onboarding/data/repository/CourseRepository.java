package jpabook.onboarding.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.status.CourseStatus;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	Optional<Course> findByIdAndStatus(Long id, CourseStatus status);

	Optional<Course> findByNameAndProfessorName(String name, String professorName);
}