package io.hhplus.architecture.response

import org.springframework.http.HttpStatus

enum class BaseResponseStatus(
    val isSuccess: Boolean,
    val status: String,
    val message: String
) {
    SUCCESS(true, HttpStatus.OK.name, "요청에 성공하였습니다."),
    NOT_FOUND_LECTURE(false, HttpStatus.NOT_FOUND.name, "존재하지 않는 특강입니다.");
}