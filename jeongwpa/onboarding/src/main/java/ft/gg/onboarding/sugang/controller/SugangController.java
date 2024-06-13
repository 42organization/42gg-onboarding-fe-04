package ft.gg.onboarding.sugang.controller;

import ft.gg.onboarding.dto.enrollment.EnrollmentResponseDto;
import ft.gg.onboarding.dto.student.StudentRequestDto;
import ft.gg.onboarding.entity.course.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SugangController {

    ResponseEntity<List<Course>> getSugang();

    ResponseEntity<Void> postSugang(int courseId, StudentRequestDto studentRequestDto);

    ResponseEntity<Void> patchSugang(int courseId, StudentRequestDto studentRequestDto);

    ResponseEntity<List<EnrollmentResponseDto>> getSugangHistory(int courseId, StudentRequestDto studentRequestDto);
}
