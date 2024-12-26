package io.hhplus.architecture.exception

import io.hhplus.architecture.response.BaseResponseStatus

open class BaseException(val status: BaseResponseStatus) : RuntimeException(status.message)
