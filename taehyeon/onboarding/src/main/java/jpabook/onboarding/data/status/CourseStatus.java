package jpabook.onboarding.data.status;

public enum CourseStatus {
	REGISTERED("등록"),
	DELETED("삭제"),
	EXPIRED("만료");

	private final String status;

	CourseStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}