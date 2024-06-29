package gg.onboarding.sugang.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gg.onboarding.data.dto.course.response.CourseListResDto;
import gg.onboarding.data.dto.course.response.CourseResDto;
import gg.onboarding.data.entity.Course;
import gg.onboarding.data.entity.Student;
import gg.onboarding.data.entity.StudentCourse;
import gg.onboarding.data.repository.CourseRepository;
import gg.onboarding.data.repository.StudentCourseRepository;
import gg.onboarding.data.repository.StudentRepository;
import gg.onboarding.data.dto.student.request.StudentReqDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SugangService {
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final StudentCourseRepository studentCourseRepository;

	@Transactional(readOnly = true)
	public CourseListResDto getCourseList() {
		List<Course> courseList = courseRepository.findAllByIsTrueIsTrue();
		List<CourseResDto> courseListResDto = courseList.stream()
			.map(CourseResDto::new)
			.collect(Collectors.toList());
		return new CourseListResDto(courseListResDto);
	}

	@Transactional
	public void enrollCourse(Long courseId, StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDate(studentReqDto.getName(),
			studentReqDto.getBirthDate()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalArgumentException("Course not found"));
		StudentCourse studentCourse = StudentCourse.toEntity(student, course);
		student.patchPlusEnrolledCredit(course.getCredit());
		studentRepository.save(student);
		studentCourseRepository.save(studentCourse);
	}

	@Transactional
	public void enrollCancelCourse(Long courseId, StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDate(studentReqDto.getName(),
			studentReqDto.getBirthDate()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalArgumentException("Course not found"));
		StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(student.getId(),
			course.getId()).orElseThrow(() -> new IllegalArgumentException("StudentCourse not found"));
		studentCourse.patchIsDeleted();
		student.patchMinusEnrolledCredit(course.getCredit());
		studentRepository.save(student);
		studentCourseRepository.save(studentCourse);
	}

	@Transactional
	public CourseListResDto getHistoryCourse(StudentReqDto studentReqDto) {
		Student student = studentRepository.findByNameAndBirthDate(studentReqDto.getName(),
			studentReqDto.getBirthDate()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
		List<StudentCourse> studentCourseList = studentCourseRepository.findByStudentId(student.getId());
		List<CourseResDto> courseListResDto = studentCourseList.stream()
			.map(StudentCourse::getCourse)
			.map(CourseResDto::new)
			.collect(Collectors.toList());
		return new CourseListResDto(courseListResDto);
	}
}
