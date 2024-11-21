package com.sample.test.onboarding.Student.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.test.onboarding.Course.Controller.Dto.Response.CourseListResDto;
import com.sample.test.onboarding.Data.Entity.Course;
import com.sample.test.onboarding.Data.Entity.Student;
import com.sample.test.onboarding.Data.Repository.StudentCourseRepository;
import com.sample.test.onboarding.Data.Repository.StudentRepository;
import com.sample.test.onboarding.Data.Status.StudentCourseStatus;
import com.sample.test.onboarding.Data.Status.StudentStatus;
import com.sample.test.onboarding.Student.Controller.Dto.Request.StudentReqDto;
import com.sample.test.onboarding.Student.Controller.Dto.Response.StudentListResDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;
	private final StudentCourseRepository studentCourseRepository;

	@Transactional
	public void enrollStudent(StudentReqDto studentReqDto) {
		// 조회 값이 있으면 에러 발생(중복처리)
		studentRepository.findByNameAndBirthDay(studentReqDto.getName(), studentReqDto.getBirthDay())
			.ifPresent(student -> {
				throw new IllegalArgumentException("Student already exists");
			});
		studentRepository.save(studentReqDto.toEntity());
	}

	@Transactional
	public void dropStudent(StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDay(studentReqDto.getName(), studentReqDto.getBirthDay())
			.orElseThrow(() -> new IllegalArgumentException("Student not found"));

		if (student.getStatus() == StudentStatus.DROPPED) {
			throw new IllegalArgumentException("Student already dropped");
		}

		student.drop();
		studentRepository.save(student);
	}

	@Transactional(readOnly = true)
	public StudentListResDto getGraduates() {
		List<Student> students = studentRepository.findByStatus(StudentStatus.GRADUATED);
		return new StudentListResDto(students);
	}

	@Transactional(readOnly = true)
	public StudentListResDto getGraduatesPage(int page) {
		final int size = 5;

		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Student> studentsPage = studentRepository.findByStatusPage(StudentStatus.GRADUATED, pageable);
		return new StudentListResDto(studentsPage.getContent(), page);
	}

	@Transactional(readOnly = true)
	public CourseListResDto getSchedule(StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDay(studentReqDto.getName(), studentReqDto.getBirthDay())
			.orElseThrow(() -> new IllegalArgumentException("Student not found"));
		// 현재 시간표 보기
		List<Course> courses = studentCourseRepository.findByStudentIdAndStatus(student.getId(),
			StudentCourseStatus.ONGOING);
		return new CourseListResDto(courses);
	}
}
