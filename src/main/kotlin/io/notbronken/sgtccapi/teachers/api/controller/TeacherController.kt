package io.notbronken.sgtccapi.teachers.api.controller

import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.teachers.api.dto.TeacherCreateDto
import io.notbronken.sgtccapi.teachers.api.dto.TeacherDto
import io.notbronken.sgtccapi.teachers.api.dto.TeacherUpdateDto
import io.notbronken.sgtccapi.teachers.business.service.TeacherService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

@RestController
@RequestMapping("/api/teachers")
class TeacherController(
    val teacherService: TeacherService,
) {
    companion object {
        private val LOGGER = Logger.getLogger(TeacherController::class.simpleName)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: TeacherCreateDto): Mono<CreatedResourceWithId> = teacherService.create(dto)

    @PutMapping(
        value = ["/{id}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @RequestBody dto: TeacherUpdateDto, @PathVariable id: String
    ): Mono<TeacherDto> = teacherService.update(dto, id)

    @GetMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: String): Mono<TeacherDto> = teacherService.read(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): Flux<TeacherDto> = teacherService.list()
}