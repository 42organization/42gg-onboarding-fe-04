package ft.gg.onboarding.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import ft.gg.onboarding.config.annotation.IntegrationTest;
import ft.gg.onboarding.dto.student.StudentCreateDto;
import ft.gg.onboarding.entity.student.Student;
import ft.gg.onboarding.entity.student.StudentStatus;
import io.swagger.v3.core.util.ObjectMapperFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc   // MockMvc 객체 자동 주입
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

        @Test
        @DisplayName("등록하는 학생의 이름이 없는 경우 400 코드를 반환합니다.")
        void returnStatusBadRequestWhenNameIsNull() throws Exception {
            // given
            LocalDate birthDate = LocalDate.of(2000, 1, 1);
            StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                    .name(null).birthDate(birthDate).build();
            String request = objectMapper.writeValueAsString(studentCreateDto);

            // expected
            mockMvc.perform(post("/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("등록하는 학생의 이름이 빈 문자열인 경우 400 코드를 반환합니다.")
        void returnStatusBadRequestWhenNameIsEmpty() throws Exception {
            // given
            LocalDate birthDate = LocalDate.of(2000, 1, 1);
            StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                    .name("").birthDate(birthDate).build();
            String request = objectMapper.writeValueAsString(studentCreateDto);

            // expected
            mockMvc.perform(post("/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("등록하는 학생의 생년월일이 없는 경우 400 코드를 반환합니다.")
        void returnStatusBadRequestWhenBirthDateIsNull() throws Exception {
            // given
            String name = "홍길동";
            StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                    .name(name).birthDate(null).build();
            String request = objectMapper.writeValueAsString(studentCreateDto);

            // expected
            mockMvc.perform(post("/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("등록하는 학생의 생년월일이 유효하지 않은 경우 400 코드를 반환합니다.")
        void returnStatusBadRequestWhenBirthDateIsInvalid() throws Exception {
            // given
            String request = "{\"name\":\"홍길동\",\"birthDate\":\"invalid\"}";

            // expected
            mockMvc.perform(post("/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("등록하는 학생의 생년월일이 미래인 경우 400 코드를 반환합니다.")
        void returnStatusBadRequestWhenBirthDateIsFuture() throws Exception {
            // given
            LocalDate birthDate = LocalDate.now().plusDays(1);
            StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                    .name("홍길동").birthDate(birthDate).build();
            String request = objectMapper.writeValueAsString(studentCreateDto);

            // expected
            mockMvc.perform(post("/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("이미 등록된 학생의 이름과 생년월일을 가진 학생을 등록하는 경우 409 코드를 반환합니다.")
        void returnStatusConflictWhenStudentAlreadyExists() throws Exception {
            // given
            String name = "홍길동";
            LocalDate birthDate = LocalDate.of(2000, 1, 1);
            Student student = Student.builder()
                    .name(name).birthDate(birthDate)
                    .totalCredit(0).enrolledCredit(0)
                    .status(StudentStatus.ATTEND)
                    .build();
            em.persist(student);
            em.flush();
            em.clear();

            StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                    .name(name).birthDate(birthDate).build();
            String request = objectMapper.writeValueAsString(studentCreateDto);

            // expected
            mockMvc.perform(post("/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isConflict());
        }
    }
}
