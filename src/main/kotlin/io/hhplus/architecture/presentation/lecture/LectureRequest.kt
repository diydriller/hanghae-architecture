package io.hhplus.architecture.presentation.lecture

import java.time.LocalDate
import java.time.LocalTime

class LectureRequest {
    data class CreateLecture(
        val title: String,

        val description: String,
    )

    data class ScheduleLecture(
        val capacity: Int,

        val instructorId: Long,

        val location: String,

        val durationMinute: Int,

        val date: LocalDate,

        val time: LocalTime
    )
}
