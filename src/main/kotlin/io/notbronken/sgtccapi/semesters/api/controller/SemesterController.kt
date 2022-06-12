package io.notbronken.sgtccapi.semesters.api.controller

import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.semesters.api.dto.CreateDto
import io.notbronken.sgtccapi.semesters.business.service.SemesterService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/semester")
class SemesterController(
    val semesterService: SemesterService,
) {
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(@RequestBody dto: CreateDto): ResponseEntity<Mono<CreatedResource>> {
        val createdResource = semesterService.createSemester(dto)
        return ResponseEntity(Mono.just(createdResource), HttpStatus.CREATED)
    }
}