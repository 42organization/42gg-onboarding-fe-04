package ft.gg.onboarding.sugang.controller;

import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.dto.student.StudentRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SugangController {

    ResponseEntity<List<Course>> getSugang();

    ResponseEntity<Void> postSugangEnroll(int courseId, StudentRequestDto studentRequestDto);

    ResponseEntity<Void> patchSugangCancel(int courseId, StudentRequestDto studentRequestDto);

    ResponseEntity<List<Course>> getSugangHistory(int courseId, StudentRequestDto studentRequestDto);
}
