package com.example.onboarding.alldata.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onboarding.alldata.entity.Student;
import com.example.onboarding.alldata.status.StudentStatus;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	boolean existsByStudentNameAndStudentBirth(String studentName, LocalDate studentBirth);

	Optional<Student> findByStudentNameAndStudentBirth(String studentName, LocalDate studentBirth);

	Page<Student> findByStudentStatus(StudentStatus studentStatus, Pageable pageable);
}