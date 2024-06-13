package gg.onboarding.student.service;

import static gg.onboarding.data.entity.custom.StudentStatus.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gg.onboarding.data.dto.course.response.CourseListResDto;
import gg.onboarding.data.dto.course.response.CourseResDto;
import gg.onboarding.data.dto.student.response.StudentListResDto;
import gg.onboarding.data.entity.Student;
import gg.onboarding.data.entity.StudentCourse;
import gg.onboarding.data.repository.StudentCourseRepository;
import gg.onboarding.data.repository.StudentRepository;
import gg.onboarding.data.dto.student.request.StudentReqDto;
import gg.onboarding.data.dto.student.response.StudentResDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;
	private final StudentCourseRepository studentCourseRepository;

	@Transactional
	public void createStudent(StudentReqDto studentReqDto) {
		studentRepository.findByNameAndBirthDate(studentReqDto.getName(), studentReqDto.getBirthDate()).orElseThrow(() -> new IllegalArgumentException("Student already exists"));
		studentRepository.save(Student.toEntity(studentReqDto));
	}

	@Transactional(readOnly = true)
	public StudentResDto getStudent(StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDate(studentReqDto.getName(), studentReqDto.getBirthDate()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
		return new StudentResDto(student);
	}

	@Transactional(readOnly = true)
	public StudentListResDto getGraduatedStudentList() {
		return new StudentListResDto(
			studentRepository.findAllByStatus(GRADUATE).stream()
				.map(StudentResDto::new)
				.collect(Collectors.toList()));
	}

	@Transactional(readOnly = true)
	public CourseListResDto getEnrollCourse(StudentReqDto studentReqDto)
	{
		Student student = studentRepository.findByNameAndBirthDate(studentReqDto.getName(), studentReqDto.getBirthDate()).orElseThrow(() -> new IllegalArgumentException("Student not found"));

		List<StudentCourse> studentCourseList = studentCourseRepository.findByStudentId(student.getId());

		List<CourseResDto> courseListResDto = studentCourseList.stream()
			.filter(studentCourse -> studentCourse.getStatus().equals("enroll"))
			.map(StudentCourse::getCourse)
			.map(CourseResDto::new)
			.collect(Collectors.toList());

		return new CourseListResDto(courseListResDto);
	}

	@Transactional
	public void dropSchool(StudentReqDto studentReqDto)	{
		Student student = studentRepository.findByNameAndBirthDate(studentReqDto.getName(), studentReqDto.getBirthDate()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
		student.drop();
		studentRepository.save(student);
	}

	@Transactional(readOnly = true)
	public CourseListResDto getFinishCourse(StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDate(studentReqDto.getName(), studentReqDto.getBirthDate()).orElseThrow(() -> new IllegalArgumentException("Student not found"));

		List<StudentCourse> studentCourseList = studentCourseRepository.findByStudentId(student.getId());

		List<CourseResDto> courseListResDto = studentCourseList.stream()
			.filter(studentCourse -> studentCourse.getStatus().equals("finish"))
			.map(StudentCourse::getCourse)
			.map(CourseResDto::new)
			.collect(Collectors.toList());

		return new CourseListResDto(courseListResDto);
	}
}
