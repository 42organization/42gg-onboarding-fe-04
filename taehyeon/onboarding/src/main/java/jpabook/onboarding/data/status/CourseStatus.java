package jpabook.onboarding.data.status;

public enum CourseStatus {
	DELETED("삭제"),
	REGISTERED("등록"),
	EXPIRED("만료");

	private final String status;

	CourseStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}