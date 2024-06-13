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
@SpringBootTest         // 테스트 환경에서 의존성 주입을 위한 설정, 의존성 주입을 위해서는 @WebMvcTest 혹은 @AutoConfigureMockMvc가 필요
@ActiveProfiles(TEST)   // testcontainers를 위한 datasource 설정
public @interface IntegrationTest {
}
