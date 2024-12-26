package io.hhplus.architecture.presentation.lecture

import io.hhplus.architecture.application.lecture.LectureService
import io.hhplus.architecture.response.BaseResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
class LectureController(
    private val lectureService: LectureService,
    private val createLectureRequestValidator: CreateLectureRequestValidator
) {
    @InitBinder("CreateLecture")
    fun initBinder(binder: WebDataBinder) {
        binder.addValidators(createLectureRequestValidator)
    }

    @PostMapping("/lecture")
    fun createLecture(
        @Validated @RequestBody request: LectureRequest.CreateLecture
    ): BaseResponse<LectureResponse.CreateLecture> {
        val lecture = lectureService.createLecture(request.toCriteria())
        val response = LectureResponse.CreateLecture.fromLecture(lecture)
        return BaseResponse(response)
    }

    @PostMapping("/lecture/{lectureId}/schedule")
    fun scheduleLecture(
        @PathVariable lectureId: Long,
        @RequestBody request: LectureRequest.ScheduleLecture
    ): BaseResponse<LectureResponse.ScheduleLecture> {
        val lectureSchedule = lectureService.scheduleLecture(request.toCriteria(lectureId))
        val response = LectureResponse.ScheduleLecture.fromLectureSchedule(lectureSchedule)
        return BaseResponse(response)
    }

    @GetMapping("/lecture")
    fun getPossibleLecture(
        @RequestParam date: LocalDate
    ): BaseResponse<List<LectureResponse.GetLectureSchedule>> {
        val scheduleList = lectureService.getPossibleLecture(date)
        val response = scheduleList.map {
            LectureResponse.GetLectureSchedule.fromLectureSchedule(it)
        }
        return BaseResponse(response)
    }
}