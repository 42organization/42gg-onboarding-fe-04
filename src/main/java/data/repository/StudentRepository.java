package data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import data.entity.Course;
import data.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Student save(Student student);              // 학생 저장

	Student findById(Integer id);               // ID로 학생 조회

	List<Student> findAll();                    // 모든 학생 조회

	void deleteById(Integer id);                // ID로 학생 삭제

	List<Student> findByIsGraduated(String status); // 특정 상태의 학생 조회
}