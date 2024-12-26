package io.hhplus.architecture.domain.lecture.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
class LectureSchedule(
    val instructorId: Long,

    var durationMinute: Int,

    var location: String,

    var date: LocalDate,

    var time: LocalTime,

    var capacity: Int,

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    val lecture: Lecture
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var enrollmentCount: Int = 0
}