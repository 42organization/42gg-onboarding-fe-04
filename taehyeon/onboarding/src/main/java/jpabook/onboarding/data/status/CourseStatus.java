package jpabook.onboarding.data.status;

public enum CourseStatus {
	REGISTERED("등록"),
	DELETED("삭제"),
	COMPLETED("완료");

	private final String status;

	CourseStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}