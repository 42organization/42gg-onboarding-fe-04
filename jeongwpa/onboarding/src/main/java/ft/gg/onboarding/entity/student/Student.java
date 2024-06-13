package ft.gg.onboarding.entity.student;

import ft.gg.onboarding.entity.base.BaseEntity;
import ft.gg.onboarding.entity.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student",
        uniqueConstraints = @UniqueConstraint(name = "unique_student_name_birth_date", columnNames = {"name", "birth_date"}),
        indexes = @Index(name = "index_student_name_birth_date", columnList = "name, birth_date", unique = true)
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "INTEGER")
    private int id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "birth_date", nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(name = "status", nullable = false, columnDefinition = "ENUM('ATTEND', 'DROP', 'GRADUATE')")
    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @Column(name = "total_credit", nullable = false, columnDefinition = "INTEGER")
    private int totalCredit;

    @Column(name = "enrolled_credit", nullable = false, columnDefinition = "INTEGER")
    private int enrolledCredit;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();

    @Builder
    public Student(int id, String name, LocalDate birthDate, StudentStatus status, int totalCredit, int enrolledCredit, List<Enrollment> enrollments) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.status = status;
        this.totalCredit = totalCredit;
        this.enrolledCredit = enrolledCredit;
        this.enrollments = enrollments;
    }

    public void dropout() {
        this.status = StudentStatus.DROP;
    }

    public void graduate() {
        this.status = StudentStatus.GRADUATE;
    }

    public void addCredit(int credit) {
        this.enrolledCredit += credit;
    }
}
