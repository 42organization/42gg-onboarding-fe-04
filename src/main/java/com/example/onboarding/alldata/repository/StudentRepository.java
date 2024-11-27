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

	Optional<Student> findByStudentNameAndStudentBirth(String studentName, int studentBirth);
	Page<Student> findByStatus(StudentStatus status, Pageable pageable);
}