package ft.gg.onboarding.sugang.service;

import ft.gg.onboarding.dto.student.StudentRequestDto;
import ft.gg.onboarding.entity.course.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SugangService {
    public List<Course> findCurrentCourses() {
        return List.of();
    }

    public void enrollCourse(int courseId, StudentRequestDto studentRequestDto) {

    }

    public void cancelCourse(int courseId, StudentRequestDto studentRequestDto) {
    }

    public List<Course> findCourseHistory(int courseId, StudentRequestDto studentRequestDto) {
        return List.of();
    }
}
