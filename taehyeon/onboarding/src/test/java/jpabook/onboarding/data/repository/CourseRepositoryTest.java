package jpabook.onboarding.data.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jpabook.onboarding.data.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest {
	@Autowired
	CourseRepository courseRepository;

	@Test
	void 강의_저장() {
		//given
		final Course course = new Course("pobi", "computer");

		//when
		final Course excepted = courseRepository.save(course);

		//then
		Assertions.assertThat(course.getName()).isEqualTo(excepted.getName());
		Assertions.assertThat(course.getProfessorName()).isEqualTo(excepted.getProfessorName());
	}

	@Test
	void 강의_찾기() {
		//given
		final Course course = new Course("pobi", "computer");

		//when
		courseRepository.save(course);
		final Course excepted = courseRepository.findById(course.getId()).orElseThrow();

		//then
		Assertions.assertThat(course.getName()).isEqualTo(excepted.getName());
		Assertions.assertThat(course.getProfessorName()).isEqualTo(excepted.getProfessorName());
	}

	@Test
	void 강의_수() {
		//given
		final Course course1 = new Course("pobi", "computer");
		final Course course2 = new Course("pobi", "math");

		//when
		courseRepository.save(course1);
		courseRepository.save(course2);
		final List<Course> courses = courseRepository.findAll();

		//then
		Assertions.assertThat(courses.size()).isEqualTo(2);
	}
}