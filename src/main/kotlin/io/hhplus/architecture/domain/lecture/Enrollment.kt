package io.hhplus.architecture.domain.lecture

import io.hhplus.architecture.domain.BaseModel
import jakarta.persistence.*

@Entity
class Enrollment(
    val userId: Long,

    @ManyToOne
    @JoinColumn(name = "lecture_schedule_id")
    val lectureSchedule: LectureSchedule,
) : BaseModel() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    var status: LectureStatus = LectureStatus.PENDING

    enum class LectureStatus {
        CANCELED, PENDING, CONFIRMED
    }

    fun enroll(){
        lectureSchedule.enrollmentCount++
        status = LectureStatus.CONFIRMED
    }
}