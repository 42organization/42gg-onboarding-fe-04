package ft.gg.onboarding.student.service.impl;

import ft.gg.onboarding.dto.student.StudentCreateDto;
import ft.gg.onboarding.dto.student.StudentPageRequestDto;
import ft.gg.onboarding.dto.student.StudentRequestDto;
import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.entity.enrollment.Enrollment;
import ft.gg.onboarding.entity.enrollment.EnrollmentStatus;
import ft.gg.onboarding.entity.student.Student;
import ft.gg.onboarding.entity.student.StudentStatus;
import ft.gg.onboarding.global.exception.custom.BusinessException;
import ft.gg.onboarding.global.exception.custom.DuplicateException;
import ft.gg.onboarding.global.exception.custom.NotFoundException;
import ft.gg.onboarding.global.utils.SortParserUtils;
import ft.gg.onboarding.repository.EnrollmentRepository;
import ft.gg.onboarding.repository.StudentRepository;
import ft.gg.onboarding.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceV2 implements StudentService {

    private final StudentRepository studentRepository;

    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public void createStudent(StudentCreateDto studentCreateDto) {
        Optional<Student> dupStudent = studentRepository.findByNameAndBirthDate(
                studentCreateDto.getName(), studentCreateDto.getBirthDate());
        if (dupStudent.isPresent()) {
            throw new DuplicateException(STUDENT_DUPLICATE_FAILED);
        }
        try {
            Student newStudent = StudentCreateDto.MapStruct.INSTANCE.toEntity(studentCreateDto);
            studentRepository.save(newStudent);
        } catch (Exception e) {
            throw new BusinessException(STUDENT_CREATE_FAILED);
        }
    }

    @Transactional(readOnly = true)
    public Student findStudentByNameAndBirthDate(StudentRequestDto studentRequestDto) {
        return studentRepository.findByNameAndBirthDate(
                        studentRequestDto.getName(), studentRequestDto.getBirthDate())
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Page<Student> findGraduatedStudents(StudentPageRequestDto studentPageRequestDto) {
        try {
            Sort sort = SortParserUtils.INSTANCE.parse(
                    studentPageRequestDto.getSort(), studentPageRequestDto.getOrder());
            PageRequest pageRequest = PageRequest.of(
                    studentPageRequestDto.getPage(), studentPageRequestDto.getSize(), sort);
            return studentRepository.findStudentsByStatusEqualsOrderByIdDesc(StudentStatus.GRADUATE, pageRequest);
        } catch (Exception e) {
            throw new BusinessException(STUDENT_GET_GRADUATES_FAILED);
        }
    }

    /**
     * With EnrollmentRepository
     *
     * <p>
     *     select e.course from Enrollment e where e.student = :student and e.status = 'ENROLL'
     * </p>
     */
    @Transactional(readOnly = true)
    public List<Course> findStudentEnrolledCourses(StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findByNameAndBirthDate(
                        studentRequestDto.getName(), studentRequestDto.getBirthDate())
                .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
        return enrollmentRepository.findEnrollmentsByStudentAndStatus(student.getId(), EnrollmentStatus.ENROLL).stream()
                .map(Enrollment::getCourse)
                .filter(Course::isTrue)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Course> findStudentFinishedCourses(StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findByNameAndBirthDate(
                        studentRequestDto.getName(), studentRequestDto.getBirthDate())
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
            Student student = studentRepository.findByNameAndBirthDate(
                            studentRequestDto.getName(), studentRequestDto.getBirthDate())
                    .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
            student.dropout();
        } catch (Exception e) {
            throw new BusinessException(STUDENT_DROP_FAILED);
        }
    }
}

