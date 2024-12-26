package io.hhplus.architecture.presentation.lecture

import io.hhplus.architecture.domain.lecture.Lecture
import io.hhplus.architecture.domain.lecture.LectureSchedule
import java.time.LocalDate
import java.time.LocalTime

class LectureResponse {
    data class CreateLecture(
        val id: Long,

        val title: String,

        val description: String
    ) {
        companion object {
            fun fromLecture(lecture: Lecture): CreateLecture {
                return CreateLecture(
                    id = lecture.id ?: throw IllegalStateException("Id must not be null"),
                    title = lecture.title,
                    description = lecture.description,
                )
            }
        }
    }

    data class ScheduleLecture(
        val id: Long,

        val capacity: Int,

        val instructorId: Long,

        val date: LocalDate,

        val time: LocalTime,

        var durationMinute: Int,

        var location: String,
    ) {
        companion object {
            fun fromLectureSchedule(lectureSchedule: LectureSchedule): ScheduleLecture {
                return ScheduleLecture(
                    id = lectureSchedule.id ?: throw IllegalStateException("Id must not be null"),
                    capacity = lectureSchedule.capacity,
                    instructorId = lectureSchedule.instructorId,
                    date = lectureSchedule.date,
                    time = lectureSchedule.time,
                    durationMinute = lectureSchedule.durationMinute,
                    location = lectureSchedule.location
                )
            }
        }
    }

    data class GetLectureSchedule(
        val lectureId: Long,

        val scheduleId: Long,

        var title: String,

        var description: String,

        var capacity: Int,

        var instructorId: Long,

        var location: String,

        var durationMinute: Int,

        val date: String,

        val time: String
    ) {
        companion object {
            fun fromLectureSchedule(lectureSchedule: LectureSchedule): GetLectureSchedule {
                return GetLectureSchedule(
                    lectureId = lectureSchedule.lecture.id ?: throw IllegalStateException("Id must not be null"),
                    scheduleId = lectureSchedule.id ?: throw IllegalStateException("Id must not be null"),
                    title = lectureSchedule.lecture.title,
                    description = lectureSchedule.lecture.description,
                    capacity = lectureSchedule.capacity,
                    instructorId = lectureSchedule.instructorId,
                    location = lectureSchedule.location,
                    durationMinute = lectureSchedule.durationMinute,
                    date = lectureSchedule.date.toString(),
                    time = lectureSchedule.time.toString()
                )
            }
        }
    }
}
