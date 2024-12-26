package io.hhplus.architecture.domain.user

import io.hhplus.architecture.domain.common.model.BaseModel
import jakarta.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,

    var email: String,

    @Enumerated(EnumType.STRING)
    var type: UserType
) : BaseModel() {
    enum class UserType {
        STUDENT, INSTRUCTOR
    }
}