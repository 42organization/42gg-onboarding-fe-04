package ft.gg.onboarding.dto.enrollment;

import ft.gg.onboarding.dto.course.CourseResponseDto;
import ft.gg.onboarding.dto.student.StudentResponseDto;
import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.entity.enrollment.Enrollment;
import ft.gg.onboarding.entity.enrollment.EnrollmentStatus;
import ft.gg.onboarding.entity.student.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Getter
@Setter
public class EnrollmentResponseDto {

    private StudentResponseDto student;

    private CourseResponseDto course;

    private EnrollmentStatus status;

    @Builder
    public EnrollmentResponseDto(StudentResponseDto student, CourseResponseDto course, EnrollmentStatus status) {
        this.student = student;
        this.course = course;
        this.status = status;
    }

    @Mapper
    public interface MapStruct {

        EnrollmentResponseDto.MapStruct INSTANCE = Mappers.getMapper(EnrollmentResponseDto.MapStruct.class);

        @Mapping(target = "student", source = "student", qualifiedByName = "studentToDto")
        @Mapping(target = "course", source = "course", qualifiedByName = "courseToDto")
        EnrollmentResponseDto toDto(Enrollment enrollment);

        @Named("studentToDto")
        default StudentResponseDto studentToDto(Student student) {
            return StudentResponseDto.MapStruct.INSTANCE.toDto(student);
        }

        @Named("courseToDto")
        default CourseResponseDto courseToDto(Course course) {
            return CourseResponseDto.MapStruct.INSTANCE.toDto(course);
        }
    }
}
