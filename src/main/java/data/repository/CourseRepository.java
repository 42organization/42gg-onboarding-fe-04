package data.repository;

import data.entity.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	Course save(Course courseRepository);

	Optional<Course> findById(Integer id);

	List<Course> findAll();

	void deleteById(Long id);

	List<Course> findByIsActive(String status);

	boolean existByOrigins(String courseTitle, String professorName, Integer curCnt);

	boolean existsByCourseTitleAndProfessorNameAndCurrentCount(String courseTitle, String professorName,
		Integer curCnt);
}
