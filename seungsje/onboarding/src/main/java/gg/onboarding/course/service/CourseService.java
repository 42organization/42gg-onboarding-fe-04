package gg.onboarding.course.service;

import static java.lang.Boolean.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gg.onboarding.data.dto.course.request.CourseReqDto;
import gg.onboarding.data.dto.course.request.SortReqDto;
import gg.onboarding.data.dto.course.response.CourseListResDto;
import gg.onboarding.data.dto.course.response.CourseResDto;
import gg.onboarding.data.entity.Student;
import gg.onboarding.data.entity.StudentCourse;
import gg.onboarding.data.repository.CourseRepository;
import gg.onboarding.data.entity.Course;
import gg.onboarding.data.repository.StudentCourseRepository;
import gg.onboarding.data.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;
	private final StudentCourseRepository studentCourseRepository;
	private final StudentRepository studentRepository;

	@Transactional(readOnly = true)
	public CourseListResDto getAllCourses(SortReqDto sortReqDto)
	{
		List<Course> courses;
		if (sortReqDto.getSort().equals("name") == TRUE && sortReqDto.getOrder().equals("asc") == TRUE)
			courses = courseRepository.findAllByOrderByNameAsc();
		else if (sortReqDto.getSort().equals("name") == TRUE && sortReqDto.getOrder().equals("desc") == FALSE)
			courses = courseRepository.findAllByOrderByNameDesc();
		else if (sortReqDto.getSort().equals("credit") == TRUE && sortReqDto.getOrder().equals("asc") == TRUE)
			courses = courseRepository.findAllByOrderByCreditAsc();
		else
			courses = courseRepository.findAllByOrderByCreditDesc();

		List<CourseResDto> courseResDtos = courses.stream()
			.map(CourseResDto::new)
			.collect(Collectors.toList());

		return new CourseListResDto(courseResDtos);
	}

	@Transactional
	public void createCourse(CourseReqDto courseReqDto) {
		Course course = new Course(courseReqDto);
		courseRepository.save(course);
	}

	@Transactional
	public void patchCourse(Long courseId, CourseReqDto courseReqDto) {
		Course course = courseRepository.findById(courseId).orElse(null);
		course.patch(courseReqDto);
		courseRepository.save(course);
	}

	@Transactional
	public void deleteCourse(Long courseId) {
		Course course = courseRepository.findById(courseId).orElse(null);
		course.delete();
		courseRepository.save(course);
	}

	@Transactional
	public void finishCourse(Long courseId) {
		List<StudentCourse> studentCourses = studentCourseRepository.findByCourseId(courseId);

		for (StudentCourse studentCourse : studentCourses) {
			studentCourse.patchIsFinish();
			studentCourseRepository.save(studentCourse);
		}

		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalArgumentException("Course not found"));

		for (StudentCourse studentCourse : studentCourses) {
			Student student = studentCourse.getStudent();
			student.patchTotalCredit(course.getCredit());
			studentRepository.save(student);
		}
	}
}
