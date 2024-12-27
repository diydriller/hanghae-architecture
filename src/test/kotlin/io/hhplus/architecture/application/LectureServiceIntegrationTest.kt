package io.hhplus.architecture.application

import io.hhplus.architecture.application.lecture.LectureService
import io.hhplus.architecture.config.BaseIntegrationTest
import io.hhplus.architecture.domain.lecture.Lecture
import io.hhplus.architecture.domain.lecture.LectureSchedule
import io.hhplus.architecture.infrastructure.lecture.LectureJpaRepository
import io.hhplus.architecture.infrastructure.lecture.LectureScheduleJpaRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalTime
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

@SpringBootTest
class LectureServiceIntegrationTest : BaseIntegrationTest() {
    @Autowired
    private lateinit var lectureService: LectureService

    @Autowired
    private lateinit var lectureJpaRepository: LectureJpaRepository

    @Autowired
    private lateinit var lectureScheduleJpaRepository: LectureScheduleJpaRepository

    @DisplayName("40명이 30명이 정원인 특강 신청할 때 실패 테스트")
    @Test
    fun applyLectureWhenExceedingCapacityFailTest() {
        // given
        val lecture = Lecture(
            "test",
            "test"
        )
        val savedLecture = lectureJpaRepository.save(lecture)
        val lectureSchedule = LectureSchedule(
            1L,
            120,
            "seoul",
            LocalDate.of(2025, 1, 20),
            LocalTime.of(18, 30),
            30,
            savedLecture
        )
        val savedLectureSchedule = lectureScheduleJpaRepository.save(lectureSchedule)

        // when
        val taskCount = 40
        val successCount = AtomicInteger(0)
        val failureCount = AtomicInteger(0)
        val userId = AtomicLong(1)
        val futureArray = Array(taskCount) {
            CompletableFuture.runAsync {
                try {
                    lectureService.applyLecture(
                        savedLectureSchedule.id ?: throw IllegalStateException("Id must not be null"),
                        userId.get()
                    )
                    successCount.incrementAndGet()
                } catch (e: Exception) {
                    failureCount.incrementAndGet()
                } finally {
                    userId.incrementAndGet()
                }
            }
        }
        CompletableFuture.allOf(*futureArray).join()

        // then
        assertEquals(30, successCount.get())
        assertEquals(10, failureCount.get())
    }


    @DisplayName("동일 유저가 동일 특강을 5번 신청할때 4번 실패하는 테스트")
    @Test
    fun applySameLectureWithSameUserFailTest() {
        // given
        val lecture = Lecture(
            "test",
            "test"
        )
        val savedLecture = lectureJpaRepository.save(lecture)
        val lectureSchedule = LectureSchedule(
            1L,
            120,
            "seoul",
            LocalDate.of(2025, 1, 20),
            LocalTime.of(18, 30),
            30,
            savedLecture
        )
        val savedLectureSchedule = lectureScheduleJpaRepository.save(lectureSchedule)

        // when
        val taskCount = 5
        val successCount = AtomicInteger(0)
        val failureCount = AtomicInteger(0)
        val userId = 3L

        val futureArray = Array(taskCount) {
            CompletableFuture.runAsync {
                try {
                    lectureService.applyLecture(
                        savedLectureSchedule.id ?: throw IllegalStateException("Id must not be null"),
                        userId
                    )
                    successCount.incrementAndGet()
                } catch (e: Exception) {
                    failureCount.incrementAndGet()
                }
            }
        }
        CompletableFuture.allOf(*futureArray).join()

        // then
        assertEquals(1, successCount.get())
        assertEquals(4, failureCount.get())
    }

}