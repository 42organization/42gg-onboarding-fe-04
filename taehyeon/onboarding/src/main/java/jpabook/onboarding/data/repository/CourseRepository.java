package jpabook.onboarding.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpabook.onboarding.data.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
