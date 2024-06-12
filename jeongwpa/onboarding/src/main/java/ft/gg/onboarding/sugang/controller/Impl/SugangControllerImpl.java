package ft.gg.onboarding.sugang.controller.Impl;

import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.dto.student.StudentRequestDto;
import ft.gg.onboarding.sugang.controller.SugangController;
import ft.gg.onboarding.sugang.service.SugangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sugang")
@RequiredArgsConstructor
public class SugangControllerImpl implements SugangController {

    private final SugangService sugangService;

    @Override
    public ResponseEntity<List<Course>> getSugang() {
        sugangService.findCurrentCourses();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> postSugangEnroll(int courseId, StudentRequestDto studentRequestDto) {
        sugangService.enrollCourse(courseId, studentRequestDto);
        return ResponseEntity.created(null).build();
    }

    @Override
    public ResponseEntity<Void> patchSugangCancel(int courseId, StudentRequestDto studentRequestDto) {
        sugangService.cancelCourse(courseId, studentRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Course>> getSugangHistory(int courseId, StudentRequestDto studentRequestDto) {
        sugangService.findCourseHistory(courseId, studentRequestDto);
        return ResponseEntity.ok().build();
    }
}
