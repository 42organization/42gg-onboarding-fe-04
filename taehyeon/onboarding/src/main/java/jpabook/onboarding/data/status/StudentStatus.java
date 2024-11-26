package jpabook.onboarding.data.status;

public enum StudentStatus {
	ENROLLED("재학"),
	DROP("중퇴"),
	GRADUATED("졸업");

	private final String status;

	StudentStatus(final String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}