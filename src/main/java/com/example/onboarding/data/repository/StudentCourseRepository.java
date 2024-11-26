package com.example.onboarding.data.repository;

import com.example.onboarding.data.entity.StudentCourse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
	StudentCourse save(StudentCourse studentCourse);       // 관계 저장// 관계 삭제
}
