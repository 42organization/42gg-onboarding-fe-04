package jpabook.onboarding.course.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jpabook.onboarding.course.controller.dto.request.CourseRequestDto;
import jpabook.onboarding.course.controller.dto.response.CourseResponseDto;
import jpabook.onboarding.course.service.CourseService;
import jpabook.onboarding.data.entity.Course;
import jpabook.onboarding.data.status.CourseStatus;

@WebMvcTest(CourseController.class)
class CourseControllerTest {
	@Autowired
	MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	CourseService courseService;

	@Test
	void 강의_생성_성공() throws Exception {
		//given
		CourseRequestDto request = new CourseRequestDto("pobi", "computer", 0);
		Course course = new Course("pobi", "computer");
		ReflectionTestUtils.setField(course, "id", 1L);
		CourseResponseDto response = new CourseResponseDto(course);
		when(courseService.create(any(CourseRequestDto.class))).thenReturn(response);

		//when & then
		mvc.perform(
				post("/courses")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name").value("computer"))
			.andExpect(jsonPath("$.professorName").value("pobi"));
	}

	@Test
	void 강의_완료_성공() throws Exception {
		//given
		Course course = new Course("pobi", "computer");
		ReflectionTestUtils.setField(course, "id", 1L);
		course.updateStatus(CourseStatus.COMPLETED);
		CourseResponseDto response = new CourseResponseDto(course);
		when(courseService.complete(1L)).thenReturn(response);

		//when & then
		mvc.perform(patch("/courses/complete/{courseId}", 1L))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value("COMPLETED"));
	}

}