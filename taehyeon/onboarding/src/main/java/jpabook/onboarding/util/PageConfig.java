package jpabook.onboarding.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageConfig {
	SIZE(5);

	private final int size;
}