package io.hhplus.architecture.application.lecture

import java.time.LocalDate
import java.time.LocalTime

class LectureCriteria {
    data class CreateLecture(
        val title: String,

        val description: String,
    )

    data class ScheduleLecture(
        val instructorId: Long,

        val location: String,

        val durationMinute: Int,

        val date: LocalDate,

        val time: LocalTime,

        val capacity: Int,
        val lectureId: Long
    )
}