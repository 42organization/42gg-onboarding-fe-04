package jpabook.onboarding.data.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jpabook.onboarding.data.status.SugangStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sugang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sugang_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private SugangStatus status;

	public Sugang(final Student student, final Course course) {
		this.student = student;
		student.getSugangs().add(this);
		this.course = course;
		this.status = SugangStatus.ONGOING;
	}

	public void updateStatus(final SugangStatus status) {
		this.status = status;
	}
}