package ft.gg.onboarding.entity;

import ft.gg.onboarding.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    @Column(name = "birth_date", nullable = false, columnDefinition = "DATETIME")
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

    public void dropout() {
        this.status = StudentStatus.DROP;
    }

    @Getter
    @RequiredArgsConstructor
    public enum StudentStatus {

        ATTEND("attend"),
        DROP("drop"),
        GRADUATE("graduate");

        private final String value;
    }
}
