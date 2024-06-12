package ft.gg.onboarding;

import org.springframework.boot.SpringApplication;

public class TestOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.from(OnboardingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
