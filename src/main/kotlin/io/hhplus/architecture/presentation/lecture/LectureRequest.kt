package io.hhplus.architecture.presentation.lecture

import io.hhplus.architecture.application.lecture.LectureCriteria
import java.time.LocalDate
import java.time.LocalTime

class LectureRequest {
    data class CreateLecture(
        val title: String,

        val description: String,
    ) {
        fun toCriteria(): LectureCriteria.CreateLecture {
            return LectureCriteria.CreateLecture(
                title = title,
                description = description
            )
        }
    }

    data class ScheduleLecture(
        val capacity: Int,

        val instructorId: Long,

        val location: String,

        val durationMinute: Int,

        val date: LocalDate,

        val time: LocalTime
    ) {
        fun toCriteria(lectureId: Long): LectureCriteria.ScheduleLecture {
            return LectureCriteria.ScheduleLecture(
                capacity = capacity,
                instructorId = instructorId,
                location = location,
                durationMinute = durationMinute,
                date = date,
                time = time,
                lectureId = lectureId
            )
        }
    }
}
