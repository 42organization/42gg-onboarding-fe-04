package com.example.onboarding.data.repository;

import com.example.onboarding.data.entity.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findByIsActive(String status);
}
