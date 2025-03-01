package io.hhplus.architecture.infrastructure.lecture

import io.hhplus.architecture.domain.lecture.Enrollment
import io.hhplus.architecture.domain.lecture.Lecture
import io.hhplus.architecture.domain.lecture.LectureSchedule
import io.hhplus.architecture.domain.lecture.LectureStore
import io.hhplus.architecture.exception.BaseException
import io.hhplus.architecture.response.BaseResponseStatus
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class LectureStoreImpl(
    private val lectureJpaRepository: LectureJpaRepository,
    private val lectureScheduleJpaRepository: LectureScheduleJpaRepository,
    private val lectureEnrollmentJpaRepository: LectureEnrollmentJpaRepository
) : LectureStore {

    override fun saveLecture(lecture: Lecture): Lecture {
        return lectureJpaRepository.save(lecture)
    }

    override fun getLecture(id: Long): Lecture {
        return lectureJpaRepository.findLectureById(id)
            ?: throw BaseException(BaseResponseStatus.NOT_FOUND_LECTURE)
    }

    override fun saveLectureSchedule(lectureSchedule: LectureSchedule): LectureSchedule {
        return lectureScheduleJpaRepository.save(lectureSchedule)
    }

    override fun getLectureForDate(date: LocalDate): List<LectureSchedule> {
        return lectureScheduleJpaRepository.findAllByDate(date)

    }

    override fun getLectureScheduleForUpdate(scheduleId: Long): LectureSchedule {
        return lectureScheduleJpaRepository.findLectureScheduleByIdForUpdate(scheduleId)
            ?: throw BaseException(BaseResponseStatus.NOT_FOUND_LECTURE_SCHEDULE)
    }

    override fun saveEnrollment(enrollment: Enrollment): Enrollment {
        return lectureEnrollmentJpaRepository.save(enrollment)
    }

    override fun existEnrollment(userId: Long, lectureSchedule: LectureSchedule): Boolean {
        return lectureEnrollmentJpaRepository.existsByUserIdAndLectureSchedule(userId, lectureSchedule)
    }

    override fun getAppliedLecture(userId: Long): LectureSchedule {
        return lectureScheduleJpaRepository.findLectureScheduleByUserIdAndStatus(
            userId,
            Enrollment.LectureStatus.CONFIRMED
        )
    }
}