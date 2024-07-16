package ft.gg.onboarding.student.controller;

import ft.gg.onboarding.dto.course.CourseResponseDto;
import ft.gg.onboarding.dto.student.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * StudentController
 */
public interface StudentController {

    ResponseEntity<Void> postStudent(StudentCreateDto studentCreateDto);

    ResponseEntity<StudentResponseDto> getStudentByNameAndBirthDate(StudentRequestDto studentRequestDto);

    ResponseEntity<StudentPageResponseDto> getStudentGraduated(StudentPageRequestDto studentPaginationRequestDto);

    ResponseEntity<List<CourseResponseDto>> getStudentEnrolledCourses(StudentRequestDto studentRequestDto);

    ResponseEntity<Void> patchStudentDrop(StudentRequestDto studentRequestDto);

    ResponseEntity<List<CourseResponseDto>> getStudentFinishedCourses(StudentRequestDto studentRequestDto);
}
