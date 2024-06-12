package ft.gg.onboarding.course.repository;

import ft.gg.onboarding.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE c.name = :name AND c.professorName = :professorName AND c.credit = :credit")
    Optional<Course> findByNameAndProfessorNameAndCredit(String name, String professorName, int credit);
}
