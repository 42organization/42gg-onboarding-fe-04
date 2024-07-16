package ft.gg.onboarding.config.annotation;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ft.gg.onboarding.global.constant.ProfileConstant.TEST;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest         // Spring Boot 전체 컨텍스트를 로드하고 테스트
@ActiveProfiles(TEST)   // testcontainers를 위한 datasource 설정
public @interface IntegrationTest {
}
