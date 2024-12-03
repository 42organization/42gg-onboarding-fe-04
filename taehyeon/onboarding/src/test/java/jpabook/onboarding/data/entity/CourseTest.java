package jpabook.onboarding.data.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import jpabook.onboarding.data.status.CourseStatus;

class CourseTest {
	@Test
	void 강의가_생성되는지_획인() {
	    //given
		final Course course = new Course("pobi", "computer");

		//when //then
		Assertions.assertThat(course.getProfessorName()).isEqualTo("pobi");
		Assertions.assertThat(course.getName()).isEqualTo("computer");
	}

	@Test
	void 강의상태_변경() {
	    //given
		final Course course = new Course("pobi", "computer");

	    //when
	    course.updateStatus(CourseStatus.COMPLETED);

	    //then
		Assertions.assertThat(course.getStatus()).isEqualTo(CourseStatus.COMPLETED);
	}
}