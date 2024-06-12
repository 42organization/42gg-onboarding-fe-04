package ft.gg.onboarding.student.service;

import ft.gg.onboarding.entity.Course;
import ft.gg.onboarding.entity.Enrollment;
import ft.gg.onboarding.entity.Enrollment.EnrollmentStatus;
import ft.gg.onboarding.entity.Student;
import ft.gg.onboarding.entity.Student.StudentStatus;
import ft.gg.onboarding.global.exception.custom.BusinessException;
import ft.gg.onboarding.global.exception.custom.DuplicateException;
import ft.gg.onboarding.global.exception.custom.NotFoundException;
import ft.gg.onboarding.student.dto.StudentCreateDto;
import ft.gg.onboarding.student.dto.StudentPageRequestDto;
import ft.gg.onboarding.student.dto.StudentRequestDto;
import ft.gg.onboarding.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private static final String STUDENT_DUPLICATE_FAILED = "Student already exists";

    private static final String STUDENT_CREATE_FAILED = "Failed to create student";

    private static final String STUDENT_NOT_FOUND = "Student not found";

    private static final String STUDENT_GET_GRADUATES_FAILED = "Failed to get graduated students";

    private static final String STUDENT_DROP_FAILED = "Failed to drop student";

    private final StudentRepository studentRepository;

    @Transactional
    public void createStudent(StudentCreateDto studentCreateDto) {
        studentRepository.findByNameAndBirthDate(studentCreateDto.getName(), studentCreateDto.getBirthDate())
                .ifPresent(student -> {
                    throw new DuplicateException(STUDENT_DUPLICATE_FAILED);
                });
        Student newStudent = StudentCreateDto.MapStruct.INSTANCE.toEntity(studentCreateDto);
        try {
            studentRepository.save(newStudent);
        } catch (Exception e) {
            throw new BusinessException(STUDENT_CREATE_FAILED);
        }
    }

    @Transactional(readOnly = true)
    public Student findStudentByNameAndBirthDate(StudentRequestDto studentRequestDto) {
        return studentRepository.findByNameAndBirthDate(studentRequestDto.getName(), studentRequestDto.getBirthDate())
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
    }


    @Transactional(readOnly = true)
    public Page<Student> findGraduatedStudents(StudentPageRequestDto studentPageRequestDto) {
        try {
            PageRequest pageRequest = PageRequest.of(
                    studentPageRequestDto.getPage(),
                    studentPageRequestDto.getSize(),
                    parseSort(studentPageRequestDto.getSort(), studentPageRequestDto.getOrder()));
            return studentRepository.findStudentsByStatusEqualsOrderByIdDesc(StudentStatus.GRADUATE, pageRequest);
        } catch (Exception e) {
            throw new BusinessException(STUDENT_GET_GRADUATES_FAILED);
        }
    }

    @Transactional(readOnly = true)
    public List<Course> findStudentEnrolledCourses(StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findByNameAndBirthDate(studentRequestDto.getName(), studentRequestDto.getBirthDate())
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
        List<Enrollment> enrollments = student.getEnrollments().stream()
                .filter(enrollment -> enrollment.getStatus().equals(EnrollmentStatus.ENROLL))
                .toList();
        return enrollments.stream()
                .map(Enrollment::getCourse)
                .filter(Course::isTrue)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Course> findStudentFinishedCourses(StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findByNameAndBirthDate(studentRequestDto.getName(), studentRequestDto.getBirthDate())
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
        List<Enrollment> enrollments = student.getEnrollments().stream()
                .filter(enrollment -> enrollment.getStatus().equals(EnrollmentStatus.SUCCESS))
                .toList();
        return enrollments.stream().map(Enrollment::getCourse)
                .filter(Course::isTrue)
                .toList();
    }

    @Transactional
    public void dropStudent(StudentRequestDto studentRequestDto) {
        try {
            Student student = studentRepository.findByNameAndBirthDate(studentRequestDto.getName(), studentRequestDto.getBirthDate())
                    .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
            student.dropout();
        } catch (Exception e) {
            throw new BusinessException(STUDENT_DROP_FAILED);
        }
    }

    private Sort parseSort(String sort, String order) {
        Sort sortObj = sort.equals("name") ? Sort.by("name") : Sort.by("id");
        return order.equals("asc") ? sortObj.ascending() : sortObj.descending();
    }
}

