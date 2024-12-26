package io.hhplus.architecture.infrastructure.lecture

import io.hhplus.architecture.domain.lecture.Lecture
import io.hhplus.architecture.domain.lecture.LectureSchedule
import io.hhplus.architecture.domain.lecture.LectureStore
import io.hhplus.architecture.exception.BaseException
import io.hhplus.architecture.response.BaseResponseStatus
import org.springframework.stereotype.Component

@Component
class LectureStoreImpl(
    private val lectureJpaRepository: LectureJpaRepository,
    private val lectureScheduleJpaRepository: LectureScheduleJpaRepository
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
}