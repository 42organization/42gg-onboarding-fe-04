package jpabook.onboarding.data.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpabook.onboarding.data.entity.Student;
import jpabook.onboarding.data.status.StudentStatus;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByNameAndBirth(String name, LocalDate birth);

	Optional<Student> findByNameAndBirthAndStatus(String name, LocalDate birth, StudentStatus status);

	Page<Student> findAllByStatus(StudentStatus status, Pageable pageable);
}