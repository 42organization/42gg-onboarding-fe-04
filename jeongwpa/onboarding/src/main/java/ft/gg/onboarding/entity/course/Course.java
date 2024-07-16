package ft.gg.onboarding.entity.course;

import ft.gg.onboarding.entity.base.BaseEntity;
import ft.gg.onboarding.entity.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course",
        uniqueConstraints = @UniqueConstraint(name = "unique_course_name_professor_name_credit", columnNames = {"name", "professor_name", "credit"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "INTEGER")
    private int id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "professor_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String professorName;

    @Column(name = "credit", nullable = false, columnDefinition = "INTEGER")
    private int credit;

    @Column(name = "is_true", nullable = false, columnDefinition = "BOOLEAN")
    private boolean isTrue;

    @Column(name = "max_student_count", nullable = false, columnDefinition = "INTEGER")
    private int maxStudentCount;

    @Column(name = "current_student_count", nullable = false, columnDefinition = "INTEGER")
    private int currentStudentCount;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    @Builder
    public Course(int id, String name, String professorName, int credit, boolean isTrue, int maxStudentCount, int currentStudentCount, List<Enrollment> enrollments) {
        this.id = id;
        this.name = name;
        this.professorName = professorName;
        this.credit = credit;
        this.isTrue = isTrue;
        this.maxStudentCount = maxStudentCount;
        this.currentStudentCount = currentStudentCount;
        this.enrollments = enrollments;
    }

    public void updateCourse(String name, String professorName, int credit, int maxStudentCount) {
        this.name = name;
        this.professorName = professorName;
        this.credit = credit;
        this.maxStudentCount = maxStudentCount;
    }

    public void deleteCourse() {
        this.isTrue = false;
    }

    public void addStudent() {
        this.currentStudentCount++;
    }
}
