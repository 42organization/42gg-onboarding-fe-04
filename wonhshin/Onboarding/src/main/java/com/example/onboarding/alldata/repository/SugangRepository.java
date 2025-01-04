package com.example.onboarding.alldata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onboarding.alldata.entity.Course;
import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.entity.Sugang;
import com.example.onboarding.alldata.status.CourseStatus;
import com.example.onboarding.alldata.status.SugangStatus;

@Repository
public interface SugangRepository extends JpaRepository<Sugang, Integer> {
	Optional<Sugang> findByStudentAndCourse(Student student, Course course);

	Page<Sugang> findByStatus(SugangStatus status, Pageable pageable);

	List<Sugang> findByStudent(Student student);

	Optional<Sugang> findByStudentAndCourseAndCourse_CourseStatus(Student student, Course course,
		CourseStatus courseStatus);
}