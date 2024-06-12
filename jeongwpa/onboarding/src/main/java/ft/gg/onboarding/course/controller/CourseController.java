package ft.gg.onboarding.course.controller;

import ft.gg.onboarding.dto.course.CourseCreateDto;
import ft.gg.onboarding.dto.course.CourseResponseDto;
import ft.gg.onboarding.dto.course.CourseUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseController {

    ResponseEntity<List<CourseResponseDto>> getCourses();

    ResponseEntity<Void> postCourse(CourseCreateDto courseCreateDto);

    ResponseEntity<Void> patchCourseUpdate(int courseId, CourseUpdateDto courseUpdateDto);

    ResponseEntity<Void> patchCourseDelete(int courseId);

    ResponseEntity<Void> patchCourseFinish(int courseId);
}
