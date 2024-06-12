package ft.gg.onboarding.repository;

import ft.gg.onboarding.entity.enrollment.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
}
