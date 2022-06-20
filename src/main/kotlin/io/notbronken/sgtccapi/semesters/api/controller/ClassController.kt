package io.notbronken.sgtccapi.semesters.api.controller

import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.semesters.api.dto.ClassCreateDto
import io.notbronken.sgtccapi.semesters.api.dto.ClassDto
import io.notbronken.sgtccapi.semesters.api.dto.ClassUpdateDto
import io.notbronken.sgtccapi.semesters.api.dto.ClassAddStudentsDto
import io.notbronken.sgtccapi.semesters.api.dto.ClassNotesDto
import io.notbronken.sgtccapi.semesters.business.service.ClassService
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
@RequestMapping("/api/classes")
class ClassController(
    val classService: ClassService,
) {
    companion object {
        private val LOGGER = Logger.getLogger(ClassController::class.simpleName)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: ClassCreateDto): Mono<CreatedResource> = classService.create(dto)

    @PutMapping(
        value = ["/{id}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @RequestBody dto: ClassUpdateDto, @PathVariable id: Long
    ): Mono<ClassDto> = classService.update(dto, id)

    @GetMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long): Mono<ClassDto> = classService.read(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): Flux<ClassDto> = classService.list()

    @PostMapping(
        value = ["/{id}/students"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun addStudents(
        @RequestBody dto: ClassAddStudentsDto, @PathVariable id: Long
    ): Mono<ClassDto> = classService.addStudents(id, dto.students)

    @PostMapping(
        value = ["/{id}/students/notes"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun addStudentNote(
        @RequestBody dto: ClassNotesDto, @PathVariable id: Long
    ): Mono<ClassDto> = classService.addNote(id, dto.studentId, dto.note)

}