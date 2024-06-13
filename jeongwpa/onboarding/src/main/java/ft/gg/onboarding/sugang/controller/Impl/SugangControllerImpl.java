package ft.gg.onboarding.sugang.controller.Impl;

import ft.gg.onboarding.dto.course.CoursePageRequestDto;
import ft.gg.onboarding.dto.course.CourseResponseDto;
import ft.gg.onboarding.dto.enrollment.EnrollmentResponseDto;
import ft.gg.onboarding.dto.student.StudentRequestDto;
import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.entity.enrollment.Enrollment;
import ft.gg.onboarding.sugang.controller.SugangController;
import ft.gg.onboarding.sugang.service.SugangService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sugang")
@RequiredArgsConstructor
public class SugangControllerImpl implements SugangController {

    private final SugangService sugangService;

    @Override
    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getSugang(
            @RequestBody @Valid CoursePageRequestDto coursePageRequestDto) {
        List<Course> courses = sugangService.findCurrentCourses(coursePageRequestDto);
        List<CourseResponseDto> coursesDto = courses.stream()
                .map(CourseResponseDto.MapStruct.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(coursesDto);
    }

    @Override
    @PostMapping("/{courseId}")
    public ResponseEntity<Void> postSugang(
            @PathVariable("courseId") int courseId,
            @RequestBody @Valid StudentRequestDto studentRequestDto) {
        sugangService.enrollCourse(courseId, studentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PatchMapping("/{courseId}")
    public ResponseEntity<Void> patchSugang(
            @PathVariable("courseId") int courseId,
            @RequestBody @Valid StudentRequestDto studentRequestDto) {
        sugangService.cancelCourse(courseId, studentRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/history")
    public ResponseEntity<List<EnrollmentResponseDto>> getSugangHistory(
            @RequestBody @Valid StudentRequestDto studentRequestDto) {
        List<Enrollment> history = sugangService.findCourseHistory(studentRequestDto);
        List<EnrollmentResponseDto> historyDto = history.stream()
                .map(EnrollmentResponseDto.MapStruct.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(historyDto);
    }
}
