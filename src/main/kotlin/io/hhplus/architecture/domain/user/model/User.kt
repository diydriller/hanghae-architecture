package io.hhplus.architecture.domain.user

import io.hhplus.architecture.domain.model.common.BaseModel
import io.hhplus.architecture.domain.lecture.Enrollment
import jakarta.persistence.*

@Entity
class User(
    var name: String,
    var email: String
) : BaseModel() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "user")
    var enrollmentList: MutableList<Enrollment> = mutableListOf()
}