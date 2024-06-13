package ft.gg.onboarding.course.service;

import ft.gg.onboarding.dto.course.CourseCreateDto;
import ft.gg.onboarding.dto.course.CoursePageRequestDto;
import ft.gg.onboarding.dto.course.CourseUpdateDto;
import ft.gg.onboarding.entity.course.Course;
import ft.gg.onboarding.entity.enrollment.Enrollment;
import ft.gg.onboarding.global.exception.custom.BusinessException;
import ft.gg.onboarding.global.exception.custom.DuplicateException;
import ft.gg.onboarding.global.exception.custom.NotFoundException;
import ft.gg.onboarding.global.utils.SortParserUtils;
import ft.gg.onboarding.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    public static final String COURSE_CREATE_FAILED = "Failed to create course";
    public static final String COURSE_UPDATE_FAILED = "Failed to update course";
    public static final String COURSE_DELETE_FAILED = "Failed to delete course";
    public static final String COURSE_FINISH_FAILED = "Failed to finish course";
    public static final String COURSE_DUPLICATE_FAILED = "Course already exists";
    public static final String COURSE_NOT_FOUND = "Course not found";

    private final CourseRepository courseRepository;


    public List<Course> getCourses(CoursePageRequestDto coursePageRequestDto) {
        SortParserUtils.INSTANCE.parse(coursePageRequestDto.getSort(), coursePageRequestDto.getOrder());
        Sort sort = SortParserUtils.INSTANCE.parse(coursePageRequestDto.getSort(), coursePageRequestDto.getOrder());
        return courseRepository.findAll(sort);
    }

    @Transactional
    public void createCourse(CourseCreateDto courseCreateDto) {
        Optional<Course> dupCourse = courseRepository.findByNameAndProfessorNameAndCredit(
                courseCreateDto.getName(), courseCreateDto.getProfessorName(), courseCreateDto.getCredit());
        if (dupCourse.isPresent()) {
            throw new DuplicateException(COURSE_DUPLICATE_FAILED);
        }
        try {
            Course newCourse = CourseCreateDto.MapStruct.INSTANCE.toEntity(courseCreateDto);
            courseRepository.save(newCourse);
        } catch (Exception e) {
            throw new BusinessException(COURSE_CREATE_FAILED);
        }
    }

    @Transactional
    public void updateCourse(int courseId, CourseUpdateDto courseUpdateDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(COURSE_NOT_FOUND));
        try {
            course.updateCourse(
                    courseUpdateDto.getName(),
                    courseUpdateDto.getProfessorName(),
                    courseUpdateDto.getCredit(),
                    courseUpdateDto.getMaxStudentCount());
        } catch (Exception e) {
            throw new BusinessException(COURSE_UPDATE_FAILED);
        }
    }

    @Transactional
    public void deleteCourse(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(COURSE_NOT_FOUND));
        try {
            course.deleteCourse();
        } catch (Exception e) {
            throw new BusinessException(COURSE_DELETE_FAILED);
        }
    }

    @Transactional
    public void finishCourse(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(COURSE_NOT_FOUND));
        try {
            course.getEnrollments().forEach(Enrollment::finishEnrollment);
        } catch (Exception e) {
            throw new BusinessException(COURSE_FINISH_FAILED);
        }
    }
}
