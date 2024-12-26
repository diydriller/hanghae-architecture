package io.hhplus.architecture.domain.lecture

import io.hhplus.architecture.domain.model.common.BaseModel
import io.hhplus.architecture.domain.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Enrollment(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    var schedule: Schedule,

    var date: LocalDateTime
) : BaseModel() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    var status: LectureStatus = LectureStatus.PENDING
}