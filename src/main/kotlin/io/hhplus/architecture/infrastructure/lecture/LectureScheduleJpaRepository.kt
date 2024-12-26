package io.hhplus.architecture.infrastructure.lecture

import io.hhplus.architecture.domain.lecture.LectureSchedule
import org.springframework.data.jpa.repository.JpaRepository
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
}