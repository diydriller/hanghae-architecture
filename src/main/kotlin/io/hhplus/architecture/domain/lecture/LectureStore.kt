package io.hhplus.architecture.domain.lecture

interface LectureStore {
    fun saveLecture(lecture: Lecture): Lecture
    fun getLecture(id: Long): Lecture
    fun saveLectureSchedule(lectureSchedule: LectureSchedule): LectureSchedule
}