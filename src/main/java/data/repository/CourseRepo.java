package data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import data.repository.CourseRepository;
import data.entity.Course;

public class CourseRepo {

	private final CourseRepository courseRepository;

	public CourseRepo(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	public Course findCourseById(Integer id) {
		Optional<Course> optionalCourse = courseRepository.findById(id);

		if (optionalCourse.isPresent()) {
			return optionalCourse.get(); // 값이 있을 때 반환
		} else {
			return null; // 값이 없을 때 null 반환
		}
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);
	}

	public List<Course> findActiveCourses(String status) {
		return courseRepository.findByIsActive(status);
	}

	public boolean isCourseDuplicated(String courseTitle, String professorName, Integer curCnt) {
		return courseRepository.existsByCourseTitleAndProfessorNameAndCurrentCount(courseTitle, professorName, curCnt);
	}
}