package data.repository;

import data.entity.StudentCourse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
	StudentCourse save(StudentCourse studentCourse);       // 관계 저장

	List<StudentCourse> findByStudentId(Integer studentId); // 특정 학생의 수강 정보 조회

	List<StudentCourse> findByCourseId(Integer courseId);   // 특정 강좌의 수강 학생 조회

	List<StudentCourse> findByStatus(String status);        // 특정 상태의 관계 조회

	void deleteById(Integer id);                            // 관계 삭제
}
