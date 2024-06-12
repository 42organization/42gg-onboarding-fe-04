package ft.gg.onboarding.student.repository;

import ft.gg.onboarding.entity.student.Student;
import ft.gg.onboarding.entity.student.StudentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.name = :name AND s.birthDate = :birthDate")
    Optional<Student> findByNameAndBirthDate(String name, LocalDate birthDate);

    Page<Student> findStudentsByStatusEqualsOrderByIdDesc(StudentStatus status, PageRequest pageRequest);
}
