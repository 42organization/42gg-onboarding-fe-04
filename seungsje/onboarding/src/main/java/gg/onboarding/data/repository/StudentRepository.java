package gg.onboarding.data.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gg.onboarding.data.entity.Student;
import gg.onboarding.data.entity.custom.StudentStatus;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByNameAndBirthDate(String name, LocalDateTime birthDate);
	Optional<Student> findAllByStatus(StudentStatus status);
}
