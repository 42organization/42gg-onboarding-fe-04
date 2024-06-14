package ft.gg.onboarding.student.controller.impl;

import ft.gg.onboarding.dto.course.CourseResponseDto;
import ft.gg.onboarding.dto.student.*;
import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.entity.student.Student;
import ft.gg.onboarding.student.controller.StudentController;
import ft.gg.onboarding.student.service.StudentService;
import ft.gg.onboarding.student.service.impl.StudentServiceV1;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentControllerImpl implements StudentController {

    private final StudentService studentService;

    @Override
    @PostMapping
    public ResponseEntity<Void> postStudent(@RequestBody @Valid StudentCreateDto studentCreateDto) {
        studentService.createStudent(studentCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<StudentResponseDto> getStudentByNameAndBirthDate(
            @RequestBody @Valid StudentRequestDto studentRequestDto) {
        Student findStudent = studentService.findStudentByNameAndBirthDate(studentRequestDto);
        return ResponseEntity.ok(StudentResponseDto.MapStruct.INSTANCE.toDto(findStudent));
    }

    @Override
    @GetMapping("/graduate")
    public ResponseEntity<StudentPageResponseDto> getStudentGraduated(
            @RequestBody @Valid StudentPageRequestDto studentPaginationRequestDto) {
        Page<Student> graduatedStudents = studentService.findGraduatedStudents(studentPaginationRequestDto);
        return ResponseEntity.ok(StudentPageResponseDto.MapStruct.INSTANCE.toDto(graduatedStudents));
    }

    @Override
    @GetMapping("/enroll")
    public ResponseEntity<List<CourseResponseDto>> getStudentEnrolledCourses(
            @RequestBody @Valid StudentRequestDto studentRequestDto) {
        List<Course> enrolledCourses = studentService.findStudentEnrolledCourses(studentRequestDto);
        List<CourseResponseDto> enrolledCoursesDto = enrolledCourses.stream()
                .map(CourseResponseDto.MapStruct.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(enrolledCoursesDto);
    }

    @Override
    @GetMapping("/finish")
    public ResponseEntity<List<CourseResponseDto>> getStudentFinishedCourses(
            @RequestBody StudentRequestDto studentRequestDto) {
        List<Course> finishedCourses = studentService.findStudentFinishedCourses(studentRequestDto);
        List<CourseResponseDto> finishedCoursesDto = finishedCourses.stream()
                .map(CourseResponseDto.MapStruct.INSTANCE::toDto)
                .toList();
        return ResponseEntity.ok(finishedCoursesDto);
    }

    @Override
    @PatchMapping("/drop")
    public ResponseEntity<Void> patchStudentDrop(@RequestBody StudentRequestDto studentRequestDto) {
        studentService.dropStudent(studentRequestDto);
        return ResponseEntity.noContent().build();
    }
}
