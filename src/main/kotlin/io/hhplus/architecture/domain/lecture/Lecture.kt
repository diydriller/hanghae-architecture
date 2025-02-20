package io.hhplus.architecture.domain.lecture

import io.hhplus.architecture.domain.BaseModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Lecture(
    var title: String,

    var description: String,
) : BaseModel() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}