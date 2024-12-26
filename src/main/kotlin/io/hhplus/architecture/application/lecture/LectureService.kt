package io.hhplus.architecture.application.lecture

import io.hhplus.architecture.domain.lecture.Lecture
import io.hhplus.architecture.domain.lecture.LectureSchedule
import io.hhplus.architecture.domain.lecture.LectureStore
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LectureService(
    private val lectureStore: LectureStore
) {
    fun createLecture(criteria: LectureCriteria.CreateLecture): Lecture {
        val lecture = Lecture(
            title = criteria.title,
            description = criteria.description
        )
        return lectureStore.saveLecture(lecture)
    }

    fun scheduleLecture(criteria: LectureCriteria.ScheduleLecture): LectureSchedule {
        val lecture = lectureStore.getLecture(criteria.lectureId)
        val lectureSchedule = LectureSchedule(
            instructorId = criteria.instructorId,
            durationMinute = criteria.durationMinute,
            location = criteria.location,
            date = criteria.date,
            time = criteria.time,
            capacity = criteria.capacity,
            lecture = lecture
        )
        return lectureStore.saveLectureSchedule(lectureSchedule)
    }

    fun getPossibleLecture(date: LocalDate): List<LectureSchedule> {
        return lectureStore.getLectureForDate(date)
    }
}