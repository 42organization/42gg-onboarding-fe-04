package ft.gg.onboarding.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import ft.gg.onboarding.dto.student.StudentCreateDto;
import io.swagger.v3.core.util.ObjectMapperFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static ft.gg.onboarding.global.constant.ProfileConstant.TEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(TEST)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class StudentIntegrationTest {

    @Autowired
    EntityManager em;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = ObjectMapperFactory.createJson();

    @Nested
    @DisplayName("POST /students")
    class PostStudent {

        @Test
        @DisplayName("학생 등록에 성공하는 경우 200 코드를 반환합니다.")
        void returnStatusOkWhenSuccess() throws Exception {
            // given
            String name = "홍길동";
            LocalDate birthDate = LocalDate.of(2000, 1, 1);
            StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                    .name(name).birthDate(birthDate).build();
            String request = objectMapper.writeValueAsString(studentCreateDto);

            // expected
            mockMvc.perform(post("/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isCreated());
        }
    }
}
