package com.sample.test.onboarding.Data.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Entity.Student;
import com.sample.test.onboarding.Data.Entity.StudentCourse;
import com.sample.test.onboarding.Data.Status.StudentCourseStatus;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

	Optional<StudentCourse> findByStudentAndCourse(Student student, Course course);

	// List<Course> findByStudentId(Long studentId);

	List<StudentCourse> findByStudentIdAndStatus(Long studentId, StudentCourseStatus status);

	List<StudentCourse> findByCourseId(Long courseId);
}
