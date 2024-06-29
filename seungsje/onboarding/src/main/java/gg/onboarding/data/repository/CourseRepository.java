package gg.onboarding.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gg.onboarding.data.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findAllByOrderByNameAsc();

	List<Course> findAllByOrderByNameDesc();

	List<Course> findAllByOrderByCreditAsc();

	List<Course> findAllByOrderByCreditDesc();

	List<Course> findAllByIsTrueIsTrue();
}

