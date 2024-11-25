package Course.Service;

import org.springframework.stereotype.Service;

import Course.Controller.DTO.req.CourseReqDto;
import data.Status.CourseStatus;
import data.repository.CourseRepository;
import data.entity.Course;
import data.repository.CourseRepository;
import data.repository.CourseRepo;
import Course.Controller.CourseController;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;

	public Course updateCourse(Integer id, CourseReqDto request) {
		Course course = courseRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다"));

		if (request.getCourseTitle() == null || request.getProfessorName() == null
			|| request.getCurrentCount() == null) {
			throw new IllegalArgumentException("입력값이 유효하지 않음");
		}

		boolean isDuplicate = courseRepository.existsByCourseTitleAndProfessorNameAndCurrentCount(
			request.getCourseTitle(),
			request.getProfessorName(),
			request.getCurrentCount()
		);
		if (isDuplicate) {
			throw new RuntimeException("중복된 강의가 존재합니다.");
		}

		course.setCourseTitle(request.getCourseTitle());
		course.setProfessorName(request.getProfessorName());
		course.setCurrentCount(request.getCurrentCount());

		return courseRepository.save(course);
	}

	public void deleteCourse(Integer id) {
		Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다"));

		if (course.getIsActive() == CourseStatus.DELETE) {
			throw new IllegalStateException("강의가 이미 삭제되었습니다");
		}
		course.setIsActive(CourseStatus.DELETE);
		courseRepository.save(course);
	}

	public void completeCourse(Integer id) {
		Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다"));

		if (course.getIsActive() == CourseStatus.EXPIRED) {
			throw new IllegalStateException("강의가 이미 완료되었습니다");
		}
		course.setIsActive(CourseStatus.EXPIRED);
		courseRepository.save(course);
	}
}
