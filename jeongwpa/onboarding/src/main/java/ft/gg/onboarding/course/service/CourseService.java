package ft.gg.onboarding.course.service;

import ft.gg.onboarding.course.repository.CourseRepository;
import ft.gg.onboarding.entity.Course;
import ft.gg.onboarding.entity.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
}
