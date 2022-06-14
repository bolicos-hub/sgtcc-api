package io.notbronken.sgtccapi.students.api.controller

import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.students.api.dto.CreateDto
import io.notbronken.sgtccapi.students.api.dto.ListDto
import io.notbronken.sgtccapi.students.business.service.StudentService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

@RestController
@RequestMapping("/api/students")
class StudentController(
    val studentService: StudentService,
) {
    companion object {
        private val LOGGER = Logger.getLogger(this::class.simpleName)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(
        @RequestBody dto: CreateDto
    ): Mono<CreatedResourceWithId> = studentService.create(dto)

    @GetMapping
    fun findAll(): Flux<List<ListDto>> = studentService.list()
}