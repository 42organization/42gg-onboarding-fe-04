package ft.gg.onboarding.entity;

import ft.gg.onboarding.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
public class StudentCourse extends BaseEntity {

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
    private StudentCourseStatus status;

    @Getter
    @RequiredArgsConstructor
    public enum StudentCourseStatus {

        ENROLL("enroll"),
        CANCEL("drop"),
        SUCCESS("success");

        private final String value;
    }
}
