package io.hhplus.architecture.domain.lecture

import io.hhplus.architecture.domain.model.common.BaseModel
import io.hhplus.architecture.domain.user.User
import jakarta.persistence.*

@Entity
class Lecture(
    var title: String,

    var description: String,

    var capacity: Int,

    @OneToOne
    @JoinColumn(name = "instructor_id")
    var instructor: User,

    @OneToMany(mappedBy = "lecture")
    val scheduleList: MutableList<Schedule> = mutableListOf()
) : BaseModel() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}