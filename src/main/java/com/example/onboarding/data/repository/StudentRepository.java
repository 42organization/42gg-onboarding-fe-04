package com.example.onboarding.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onboarding.data.entity.Course;
import com.example.onboarding.data.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}