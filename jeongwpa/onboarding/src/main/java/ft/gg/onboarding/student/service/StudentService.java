package ft.gg.onboarding.student.service;

import ft.gg.onboarding.dto.student.StudentCreateDto;
import ft.gg.onboarding.dto.student.StudentPageRequestDto;
import ft.gg.onboarding.dto.student.StudentRequestDto;
import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.entity.student.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    String STUDENT_DUPLICATE_FAILED = "Student already exists";
    String STUDENT_CREATE_FAILED = "Failed to create student";
    String STUDENT_NOT_FOUND = "Student not found";
    String STUDENT_GET_GRADUATES_FAILED = "Failed to get graduated students";
    String STUDENT_DROP_FAILED = "Failed to drop student";

    void createStudent(StudentCreateDto studentCreateDto);

    Student findStudentByNameAndBirthDate(StudentRequestDto studentRequestDto);

    Page<Student> findGraduatedStudents(StudentPageRequestDto studentPageRequestDto);

    List<Course> findStudentEnrolledCourses(StudentRequestDto studentRequestDto);

    List<Course> findStudentFinishedCourses(StudentRequestDto studentRequestDto);

    void dropStudent(StudentRequestDto studentRequestDto);
}
