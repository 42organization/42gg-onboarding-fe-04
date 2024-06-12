package ft.gg.onboarding.entity;

import ft.gg.onboarding.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
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

    @Column(name = "max_student_count", nullable = false, columnDefinition = "INTEGER")
    private int maxStudentCount;

    @Column(name = "current_student_count", nullable = false, columnDefinition = "INTEGER")
    private int currentStudentCount;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> studentCourses = new ArrayList<>();
}
