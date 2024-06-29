package gg.onboarding.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gg.onboarding.data.entity.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
	Optional<StudentCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);

	List<StudentCourse> findByStudentId(Long id);
	List<StudentCourse> findByCourseId(Long id);
}
