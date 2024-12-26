package io.hhplus.architecture.application

import io.hhplus.architecture.application.lecture.LectureCriteria
import io.hhplus.architecture.application.lecture.LectureService
import io.hhplus.architecture.domain.lecture.Lecture
import io.hhplus.architecture.domain.lecture.LectureSchedule
import io.hhplus.architecture.exception.BaseException
import io.hhplus.architecture.infrastructure.lecture.LectureStoreImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import java.time.LocalDate
import java.time.LocalTime

@ExtendWith(MockitoExtension::class)
class LectureServiceUnitTest {

    @InjectMocks
    private lateinit var lectureService: LectureService

    @Mock
    private lateinit var lectureStore: LectureStoreImpl

    @DisplayName("Lecture 생성 성공 테스트")
    @Test
    fun createLectureSuccessTest() {
        // given
        val criteria = LectureCriteria.CreateLecture(
            title = "test",
            description = "test",
        )

        val savedLecture = Lecture(
            title = criteria.title,
            description = criteria.description,
        )
        savedLecture.id = 1L

        `when`(lectureStore.saveLecture(any())).thenReturn(savedLecture)

        // when
        val result = lectureService.createLecture(criteria)

        // then
        assertNotNull(result)
        assertEquals(1L, result.id)
        assertEquals(criteria.title, result.title)
        assertEquals(criteria.description, result.description)

        verify(lectureStore, times(1)).saveLecture(any())
    }

    @DisplayName("LectureSchedule 생성 성공 테스트")
    @Test
    fun createLectureScheduleSuccessTest() {
        val criteria = LectureCriteria.ScheduleLecture(
            capacity = 20,
            instructorId = 1L,
            location = "seoul",
            durationMinute = 120,
            date = LocalDate.of(2025, 1, 20),
            time = LocalTime.of(16, 30),
            lectureId = 1L
        )
        val savedLecture = Lecture(
            title = "test",
            description = "test",
        )
        savedLecture.id = 1L

        val savedLectureSchedule = LectureSchedule(
            capacity = criteria.capacity,
            instructorId = criteria.instructorId,
            location = criteria.location,
            durationMinute = criteria.durationMinute,
            date = criteria.date,
            time = criteria.time,
            lecture = savedLecture
        )
        savedLectureSchedule.id = 1L

        `when`(lectureStore.getLecture(criteria.lectureId)).thenReturn(savedLecture)
        `when`(lectureStore.saveLectureSchedule(any())).thenReturn(savedLectureSchedule)

        // when
        val result = lectureService.scheduleLecture(criteria)

        // then
        assertNotNull(result)
        assertEquals(1L, result.id)
        assertEquals(criteria.capacity, result.capacity)
        assertEquals(criteria.instructorId, result.instructorId)
        assertEquals(criteria.location, result.location)
        assertEquals(criteria.durationMinute, result.durationMinute)

        verify(lectureStore, times(1)).saveLectureSchedule(any())
    }

    @DisplayName("이미 신청한 특강 신청 실패 테스트")
    @Test
    fun alreadyAppliedLectureFailTest() {
        // given
        val userId = 1L
        val scheduleId = 1L

        val criteria = LectureCriteria.ScheduleLecture(
            capacity = 20,
            instructorId = 1L,
            location = "seoul",
            durationMinute = 120,
            date = LocalDate.of(2025, 1, 20),
            time = LocalTime.of(16, 30),
            lectureId = 1L
        )
        val savedLecture = Lecture(
            title = "test",
            description = "test",
        )
        savedLecture.id = 1L

        val savedLectureSchedule = LectureSchedule(
            capacity = criteria.capacity,
            instructorId = criteria.instructorId,
            location = criteria.location,
            durationMinute = criteria.durationMinute,
            date = criteria.date,
            time = criteria.time,
            lecture = savedLecture
        )
        savedLectureSchedule.id = scheduleId
        savedLectureSchedule.enrollmentCount = 10

        `when`(lectureStore.getLectureScheduleForUpdate(scheduleId)).thenReturn(savedLectureSchedule)
        `when`(lectureStore.existEnrollment(userId, savedLectureSchedule)).thenReturn(true)

        // when & then
        assertThrows(BaseException::class.java) {
            lectureService.applyLecture(scheduleId, userId)
        }
    }

    @DisplayName("정원 초과한 특강 신청 실패 테스트")
    @Test
    fun exceedCapacityFailTest(){
        // given
        val userId = 1L
        val scheduleId = 1L

        val criteria = LectureCriteria.ScheduleLecture(
            capacity = 20,
            instructorId = 1L,
            location = "seoul",
            durationMinute = 120,
            date = LocalDate.of(2025, 1, 20),
            time = LocalTime.of(16, 30),
            lectureId = 1L
        )
        val savedLecture = Lecture(
            title = "test",
            description = "test",
        )
        savedLecture.id = 1L

        val savedLectureSchedule = LectureSchedule(
            capacity = criteria.capacity,
            instructorId = criteria.instructorId,
            location = criteria.location,
            durationMinute = criteria.durationMinute,
            date = criteria.date,
            time = criteria.time,
            lecture = savedLecture
        )
        savedLectureSchedule.id = scheduleId
        savedLectureSchedule.enrollmentCount = 20

        `when`(lectureStore.getLectureScheduleForUpdate(scheduleId)).thenReturn(savedLectureSchedule)

        // when & then
        assertThrows(BaseException::class.java) {
            lectureService.applyLecture(scheduleId, userId)
        }
    }
}