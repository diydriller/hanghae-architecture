package io.hhplus.architecture.presentation.lecture

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator


@Component
class CreateLectureRequestValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return LectureRequest.CreateLecture::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        val request = target as LectureRequest.CreateLecture

        if (request.title.isBlank()) {
            errors.rejectValue("title", "NotBlank", "Title must not be blank")
        }

        if (request.description.isBlank()) {
            errors.rejectValue("description", "NotBlank", "Description must not be blank")
        }
    }
}