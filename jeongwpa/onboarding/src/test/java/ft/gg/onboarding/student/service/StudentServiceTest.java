package ft.gg.onboarding.student.service;

import ft.gg.onboarding.dto.student.StudentCreateDto;
import ft.gg.onboarding.dto.student.StudentPageRequestDto;
import ft.gg.onboarding.dto.student.StudentRequestDto;
import ft.gg.onboarding.entity.student.Student;
import ft.gg.onboarding.global.exception.custom.DuplicateException;
import ft.gg.onboarding.global.exception.custom.NotFoundException;
import ft.gg.onboarding.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Nested
    @DisplayName("학생 등록")
    class CreateStudent {

        @Test
        @DisplayName("성공하는 경우 아무것도 반환하지 않습니다.")
        void DoesNotReturnAnything() {
            // given
            StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                    .name("홍길동")
                    .birthDate(LocalDate.of(2000, 1, 1))
                    .build();
            when(studentRepository.findByNameAndBirthDate(anyString(), any(LocalDate.class)))
                    .thenReturn(Optional.empty());
            when(studentRepository.save(any())).thenReturn(null);

            // expected
            Assertions.assertDoesNotThrow(() -> studentService.createStudent(studentCreateDto));
        }

        @Test
        @DisplayName("이미 등록된 학생인 경우 DuplicateException을 던집니다.")
        void ThrowsDuplicateExceptionWhenStudentAlreadyExists() {
            // given
            StudentCreateDto studentCreateDto = StudentCreateDto.builder()
                    .name("홍길동")
                    .birthDate(LocalDate.of(2000, 1, 1))
                    .build();
            Student student = StudentCreateDto.MapStruct.INSTANCE.toEntity(studentCreateDto);
            when(studentRepository.findByNameAndBirthDate(anyString(), any(LocalDate.class)))
                    .thenReturn(Optional.of(student));

            // expected
            Assertions.assertThrows(DuplicateException.class,
                    () -> studentService.createStudent(studentCreateDto));
        }
    }

    @Nested
    @DisplayName("학생 조회")
    class FindStudent {

        @Test
        @DisplayName("성공하는 경우 학생을 반환합니다.")
        void ReturnStudent() {
            // given
            String name = "홍길동";
            LocalDate birthDate = LocalDate.of(2000, 1, 1);
            Student student = Student.builder().name(name).birthDate(birthDate).build();
            StudentRequestDto studentRequestDto = StudentRequestDto.builder()
                    .name(name).birthDate(birthDate).build();
            when(studentRepository.findByNameAndBirthDate(anyString(), any(LocalDate.class)))
                    .thenReturn(Optional.of(student));

            // when
            Student response = studentService.findStudentByNameAndBirthDate(studentRequestDto);

            // then
            Assertions.assertEquals(student.getName(), response.getName());
            Assertions.assertEquals(student.getBirthDate(), response.getBirthDate());
        }

        @Test
        @DisplayName("학생을 찾을 수 없는 경우 NotFoundException을 던집니다.")
        void ThrowsNotFoundExceptionWhenStudentNotFound() {
            // given
            String name = "홍길동";
            LocalDate birthDate = LocalDate.of(2000, 1, 1);
            StudentRequestDto studentRequestDto = StudentRequestDto.builder()
                    .name(name).birthDate(birthDate).build();
            when(studentRepository.findByNameAndBirthDate(anyString(), any(LocalDate.class)))
                    .thenReturn(Optional.empty());

            // expected
            Assertions.assertThrows(NotFoundException.class,
                    () -> studentService.findStudentByNameAndBirthDate(studentRequestDto));
        }
    }
}