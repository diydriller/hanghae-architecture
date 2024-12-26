package io.hhplus.architecture.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import io.hhplus.architecture.config.BaseIntegrationTest
import io.hhplus.architecture.presentation.lecture.LectureRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class LectureControllerIntegrationTest : BaseIntegrationTest() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @DisplayName("특강 생성 성공 테스트")
    @Test
    fun createLectureSuccessTest() {
        // given
        val request = LectureRequest.CreateLecture(
            title = "test",
            description = "test"
        )
        val requestJson = objectMapper.writeValueAsString(request)

        // when & then
        mockMvc.perform(
            post("/lecture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(status().isOk)
    }
}