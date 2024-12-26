package io.hhplus.architecture.domain.lecture

import io.hhplus.architecture.domain.model.common.BaseModel
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Schedule(
    var durationMinute: Int,

    var location: String,

    var date: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    val lecture: Lecture
) : BaseModel() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "schedule")
    var enrollmentList: MutableList<Enrollment> = mutableListOf()
}