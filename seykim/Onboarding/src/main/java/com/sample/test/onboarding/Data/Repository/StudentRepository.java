package com.sample.test.onboarding.Data.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.test.onboarding.Data.Entity.Student;
import com.sample.test.onboarding.Data.Status.StudentStatus;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByNameAndBirthDay(String name, LocalDateTime birthDay);

	List<Student> findByStatus(StudentStatus status);

	Page<Student> findByStatusPage(StudentStatus status, Pageable pageable);
}
