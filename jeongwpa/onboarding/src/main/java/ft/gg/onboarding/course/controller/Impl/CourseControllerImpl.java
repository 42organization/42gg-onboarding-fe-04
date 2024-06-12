package ft.gg.onboarding.course.controller.Impl;

import ft.gg.onboarding.course.controller.CourseController;
import ft.gg.onboarding.course.dto.CourseCreateDto;
import ft.gg.onboarding.course.dto.CourseResponseDto;
import ft.gg.onboarding.course.dto.CourseUpdateDto;
import ft.gg.onboarding.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseControllerImpl implements CourseController {

    private final CourseService courseService;

    @Override
    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getCourses() {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> postCourse(@RequestBody CourseCreateDto courseCreateDto) {
        courseService.createCourse(courseCreateDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{courseId}")
    public ResponseEntity<Void> patchCourseUpdate(
            @PathVariable("courseId") @Valid int courseId,
            @RequestBody CourseUpdateDto courseUpdateDto) {
        courseService.updateCourse(courseId, courseUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{courseId}/delete")
    public ResponseEntity<Void> patchCourseDelete(@PathVariable("courseId") @Valid int courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{courseId}/finish")
    public ResponseEntity<Void> patchCourseFinish(@PathVariable("courseId") @Valid int courseId) {
        courseService.finishCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
