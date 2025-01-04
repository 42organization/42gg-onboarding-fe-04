package com.sample.test.onboarding.Student.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.test.onboarding.Course.Controller.Dto.Response.CourseListResDto;
import com.sample.test.onboarding.Course.Controller.Dto.Response.CourseResDto;
import com.sample.test.onboarding.Data.Entity.Student;
import com.sample.test.onboarding.Data.Entity.StudentCourse;
import com.sample.test.onboarding.Data.Exception.CustomException;
import com.sample.test.onboarding.Data.Exception.ErrorResponse;
import com.sample.test.onboarding.Data.Repository.StudentCourseRepository;
import com.sample.test.onboarding.Data.Repository.StudentRepository;
import com.sample.test.onboarding.Data.Status.StudentCourseStatus;
import com.sample.test.onboarding.Data.Status.StudentStatus;
import com.sample.test.onboarding.Student.Controller.Dto.Request.StudentReqDto;
import com.sample.test.onboarding.Student.Controller.Dto.Response.StudentListResDto;
import com.sample.test.onboarding.Student.Controller.Dto.Response.StudentResDto;

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
				throw new CustomException(ErrorResponse.STUDENT_ALREADY_EXISTS);
			});
		studentRepository.save(studentReqDto.toEntity());
	}

	@Transactional
	public void dropStudent(StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDay(studentReqDto.getName(), studentReqDto.getBirthDay())
			.orElseThrow(() -> new CustomException(ErrorResponse.STUDENT_NOT_FOUND));

		if (student.getStatus() == StudentStatus.DROPPED) {
			throw new CustomException(ErrorResponse.STUDENT_ALREADY_DROPPED);
		}

		student.drop();
		studentRepository.save(student);
	}

	@Transactional(readOnly = true)
	public StudentListResDto getGraduates() {
		List<Student> students = studentRepository.findByStatus(StudentStatus.GRADUATED);
		List<StudentResDto> studentResDtoList = students.stream().map(StudentResDto::new).toList();
		return new StudentListResDto(studentResDtoList);
	}

	@Transactional(readOnly = true)
	public StudentListResDto getGraduatesPage(int page) {
		final int size = 5;

		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Student> studentsPage = studentRepository.findByStatus(StudentStatus.GRADUATED, pageable);
		List<StudentResDto> studentResDtoList = studentsPage.getContent().stream().map(StudentResDto::new).toList();
		return new StudentListResDto(studentResDtoList, page);
	}

	@Transactional(readOnly = true)
	public CourseListResDto getSchedule(StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDay(studentReqDto.getName(), studentReqDto.getBirthDay())
			.orElseThrow(() -> new CustomException(ErrorResponse.STUDENT_NOT_FOUND));
		// 현재 시간표 보기
		List<StudentCourse> studentCourses = studentCourseRepository.findByStudentIdAndStatus(student.getId(),
			StudentCourseStatus.ONGOING);
		List<CourseResDto> courseResDtoList = studentCourses.stream()
			.map(StudentCourse::getCourse).map(CourseResDto::new)
			.collect(Collectors.toList());

		return new CourseListResDto(courseResDtoList);
	}
}
