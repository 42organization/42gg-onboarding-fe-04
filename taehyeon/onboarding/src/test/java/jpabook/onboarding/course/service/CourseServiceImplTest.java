package jpabook.onboarding.course.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;
import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.repository.CourseRepository;
import jpabook.onboarding.data.repository.SugangRepository;
import jpabook.onboarding.data.status.CourseStatus;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
	@InjectMocks
	CourseServiceImpl courseService;

	@Mock
	CourseRepository courseRepository;

	@Mock
	SugangRepository sugangRepository;

	@Test
	void 강의_생성() {
		//given
		CourseRequestDto requestDto = new CourseRequestDto("pobi", "computer", 0);

		//when
		when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> {
			Course savedCourse = invocation.getArgument(0);
			ReflectionTestUtils.setField(savedCourse, "id", 1L);
			return savedCourse;
		});
		CourseResponseDto response = courseService.create(requestDto);

		//then
		assertThat(response.getName()).isEqualTo("computer");
		assertThat(response.getProfessorName()).isEqualTo("pobi");
		verify(courseRepository).save(any(Course.class));
	}

	@Test
	void 강의_완료() {
		//given
		Course course = new Course("pobi", "computer");
		ReflectionTestUtils.setField(course, "id", 1L);

		//when
		when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
		CourseResponseDto response = courseService.complete(1L);

		//then
		assertThat(response.getStatus()).isEqualTo(CourseStatus.COMPLETED);
		verify(courseRepository).findById(1L);
	}
}