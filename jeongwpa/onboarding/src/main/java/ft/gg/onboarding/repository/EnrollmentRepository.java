package ft.gg.onboarding.repository;

import ft.gg.onboarding.entity.enrollment.Enrollment;
import ft.gg.onboarding.entity.enrollment.EnrollmentStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    @Query("SELECT e FROM Enrollment e WHERE e.course.id = :courseId AND e.student.id = :studentId")
    Optional<Enrollment> findByCourseIdAndStudentId(int courseId, int studentId);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId")
    List<Enrollment> findEnrollmentsByStudentId(int studentId);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.status = :status")
    List<Enrollment> findEnrollmentsByStudentAndStatus(int studentId, EnrollmentStatus status);
}
