package ft.gg.onboarding.entity.enrollment;

import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.entity.base.BaseEntity;
import ft.gg.onboarding.entity.student.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enrollment", indexes = {
        @Index(name = "index_enrollment_student_id", columnList = "student_id"),
        @Index(name = "index_enrollment_course_id", columnList = "course_id"),
        @Index(name = "index_enrollment_student_id_course_id", columnList = "student_id, course_id", unique = true)
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "INTEGER")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "status", nullable = false, columnDefinition = "ENUM('enroll', 'cancel', 'success')")
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @Builder
    public Enrollment(int id, Student student, Course course, EnrollmentStatus status) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.status = status;
    }

    public static Enrollment makeEnroll(Student student, Course course) {
        return Enrollment.builder()
                .student(student)
                .course(course)
                .status(EnrollmentStatus.ENROLL)
                .build();
    }

    public void finishEnrollment() {
        this.status = EnrollmentStatus.SUCCESS;
    }

    public void cancelEnrollment() {
        this.status = EnrollmentStatus.CANCEL;
    }
}
