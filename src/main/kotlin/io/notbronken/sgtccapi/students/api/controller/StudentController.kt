package io.notbronken.sgtccapi.students.api.controller

import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.students.api.dto.StudentCreateDto
import io.notbronken.sgtccapi.students.api.dto.StudentDto
import io.notbronken.sgtccapi.students.api.dto.StudentUpdateDto
import io.notbronken.sgtccapi.students.business.service.StudentService
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
@RequestMapping("/api/students")
class StudentController(
    val studentService: StudentService,
) {
    companion object {
        private val LOGGER = Logger.getLogger(StudentController::class.simpleName)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: StudentCreateDto): Mono<CreatedResourceWithId> = studentService.create(dto)

    @PutMapping(
        value = ["/{id}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @RequestBody dto: StudentUpdateDto, @PathVariable id: String
    ): Mono<StudentDto> = studentService.update(dto, id)

    @GetMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: String): Mono<StudentDto> = studentService.read(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): Flux<StudentDto> = studentService.list()
}