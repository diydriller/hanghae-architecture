package io.hhplus.architecture.infrastructure.lecture

import io.hhplus.architecture.domain.lecture.Enrollment
import io.hhplus.architecture.domain.lecture.LectureSchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LectureEnrollmentJpaRepository : JpaRepository<Enrollment, Long> {
    fun existsByUserIdAndLectureSchedule(userId: Long, lectureSchedule: LectureSchedule): Boolean
}