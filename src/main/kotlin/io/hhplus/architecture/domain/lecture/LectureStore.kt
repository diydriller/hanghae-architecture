package io.hhplus.architecture.domain.lecture

import java.time.LocalDate

interface LectureStore {
    fun saveLecture(lecture: Lecture): Lecture
    fun getLecture(id: Long): Lecture
    fun saveLectureSchedule(lectureSchedule: LectureSchedule): LectureSchedule
    fun getLectureForDate(date: LocalDate): List<LectureSchedule>
    fun getLectureScheduleForUpdate(scheduleId: Long): LectureSchedule
    fun saveEnrollment(enrollment: Enrollment): Enrollment
    fun existEnrollment(userId: Long, lectureSchedule: LectureSchedule): Boolean
}