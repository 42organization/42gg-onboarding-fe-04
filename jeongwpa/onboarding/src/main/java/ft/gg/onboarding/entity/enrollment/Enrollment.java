package ft.gg.onboarding.entity.enrollment;

import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.entity.base.BaseEntity;
import ft.gg.onboarding.entity.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "enrollment")
@Getter
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

    public void finishEnrollment() {
        this.status = EnrollmentStatus.SUCCESS;
    }

    public void cancelEnrollment() {
        this.status = EnrollmentStatus.CANCEL;
    }
}
