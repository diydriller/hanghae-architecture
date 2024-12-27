package io.hhplus.architecture.presentation

import io.hhplus.architecture.presentation.lecture.CreateLectureRequestValidator
import io.hhplus.architecture.presentation.lecture.LectureRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.validation.BeanPropertyBindingResult

class LectureRequestValidatorUnitTest {
    private val validator = CreateLectureRequestValidator()

    @DisplayName("특강 생성시 요청값 검증 실패 테스트")
    @Test
    fun validateCreateLectureFailTest() {
        // given
        val request = LectureRequest.CreateLecture(
            title = "test",
            description = ""
        )
        val errors = BeanPropertyBindingResult(request, "CreateLecture")

        // when
        validator.validate(request, errors)

        // then
        assertThat(errors.errorCount).isEqualTo(1)
    }
}