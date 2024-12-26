package io.hhplus.architecture.infrastructure.lecture

import io.hhplus.architecture.domain.lecture.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LectureJpaRepository : JpaRepository<Lecture, Long> {
    fun findLectureById(id: Long): Lecture?
}