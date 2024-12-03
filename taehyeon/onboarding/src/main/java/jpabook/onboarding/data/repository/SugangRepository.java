package jpabook.onboarding.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.entity.Sugang;
import jpabook.onboarding.data.status.SugangStatus;

@Repository
public interface SugangRepository extends JpaRepository<Sugang, Long> {
	Optional<Sugang> findByStudentAndCourse(Student student, Course course);

	List<Sugang> findAllByCourseIdAndStatus(Long courseId, SugangStatus status);
}