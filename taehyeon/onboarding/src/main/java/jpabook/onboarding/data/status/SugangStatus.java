package jpabook.onboarding.data.status;

public enum SugangStatus {
	ONGOING("진행중"),
	CANCELED("취소"),
	COMPLETED("완료");

	private final String status;

	SugangStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}