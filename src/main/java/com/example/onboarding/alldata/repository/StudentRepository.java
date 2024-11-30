package com.example.onboarding.alldata.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.example.onboarding.alldata.status.StudentStatus;
import com.example.onboarding.alldata.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	boolean existsByStudentNameAndStudentBirth(String studentName, Integer studentBirth);
	Optional<Student> findByStudentNameAndStudentBirth(String studentName, Integer studentBirth);
	Page<Student> findByStudentStatus(StudentStatus studentStatus, Pageable pageable);
}