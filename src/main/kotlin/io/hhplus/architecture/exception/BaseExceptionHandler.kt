package io.hhplus.architecture.exception

import io.hhplus.architecture.response.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BaseExceptionHandler {
    @ExceptionHandler(BaseException::class)
    @ResponseBody
    fun handleNotFoundException(ex: BaseException): ResponseEntity<BaseResponse<Any>> {
        return ResponseEntity.status(HttpStatus.valueOf(ex.status.status)).body(
            BaseResponse(
                isSuccess = false,
                message = ex.status.message
            )
        )
    }

    @ExceptionHandler(BindException::class)
    fun handleValidationExceptions(ex: BindException): ResponseEntity<Map<String, String>> {
        val errors = ex.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Invalid value") }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }
}