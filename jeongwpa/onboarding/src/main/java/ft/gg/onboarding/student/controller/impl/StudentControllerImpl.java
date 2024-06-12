package ft.gg.onboarding.student.controller.impl;

import ft.gg.onboarding.course.dto.CourseResponseDto;
import ft.gg.onboarding.entity.Student;
import ft.gg.onboarding.student.controller.StudentController;
import ft.gg.onboarding.student.dto.*;
import ft.gg.onboarding.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Void> postStudent(@ModelAttribute StudentCreateDto studentCreateDto) {
        studentService.createStudent(studentCreateDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<StudentResponseDto> getStudentByNameAndBirthDate(StudentRequestDto studentRequestDto) {
        Student findStudent = studentService.findStudentByNameAndBirthDate(studentRequestDto);
        return ResponseEntity.ok(StudentResponseDto.MapStruct.INSTANCE.toDto(findStudent));
    }

    @Override
    @GetMapping("/graduate")
    public ResponseEntity<StudentPageResponseDto> getStudentGraduated(
            StudentPageRequestDto studentPaginationRequestDto) {
        Page<Student> graduatedStudents = studentService.findGraduatedStudents(studentPaginationRequestDto);
        return ResponseEntity.ok(StudentPageResponseDto.MapStruct.INSTANCE.toDto(graduatedStudents));
    }

    @Override
    public ResponseEntity<List<CourseResponseDto>> getStudentEnrolledCourses(StudentRequestDto studentRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> patchStudentDrop(StudentRequestDto studentRequestDto) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<CourseResponseDto>> getStudentFinishedCourses(int id) {
        return null;
    }
}
