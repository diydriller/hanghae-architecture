package io.hhplus.architecture.infrastructure.repository.lecture

import io.hhplus.architecture.domain.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LectureRepository : JpaRepository<Lecture, Long> {
}