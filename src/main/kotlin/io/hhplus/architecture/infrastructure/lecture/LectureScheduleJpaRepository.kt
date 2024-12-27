package io.hhplus.architecture.infrastructure.lecture

import io.hhplus.architecture.domain.lecture.Enrollment
import io.hhplus.architecture.domain.lecture.LectureSchedule
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface LectureScheduleJpaRepository : JpaRepository<LectureSchedule, Long> {
    @Query(
        "SELECT ls FROM LectureSchedule ls JOIN FETCH ls.lecture " +
                "WHERE ls.date = :date "
    )
    fun findAllByDate(date: LocalDate): List<LectureSchedule>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ls FROM LectureSchedule ls WHERE ls.id = :id")
    fun findLectureScheduleByIdForUpdate(id: Long): LectureSchedule?

    @Query("SELECT ls FROM Enrollment e JOIN e.lectureSchedule ls " +
            "WHERE e.userId =:userId AND e.status = :status")
    fun findLectureScheduleByUserIdAndStatus(userId: Long, status: Enrollment.LectureStatus): LectureSchedule
}