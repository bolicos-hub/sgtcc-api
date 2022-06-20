package io.notbronken.sgtccapi.teachers.api.controller

import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.teachers.api.dto.SuggestionCreateDto
import io.notbronken.sgtccapi.teachers.api.dto.SuggestionDto
import io.notbronken.sgtccapi.teachers.api.dto.SuggestionUpdateDto
import io.notbronken.sgtccapi.teachers.business.service.SuggestionService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
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
@RequestMapping("/api/suggestions")
class SuggestionController(
    val suggestionService: SuggestionService,
) {
    companion object {
        private val LOGGER = Logger.getLogger(SuggestionController::class.simpleName)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: SuggestionCreateDto): Mono<CreatedResource> = suggestionService.create(dto)

    @PutMapping(
        value = ["/{id}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @RequestBody dto: SuggestionUpdateDto, @PathVariable id: Long
    ): Mono<SuggestionDto> = suggestionService.update(dto, id)

    @GetMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long): Mono<SuggestionDto> = suggestionService.read(id)

    @DeleteMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long): Mono<Void> = suggestionService.delete(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): Flux<SuggestionDto> = suggestionService.list()

    @GetMapping(
        value = ["/teacher/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    @ResponseStatus(HttpStatus.OK)
    fun findAllByTeacherId(@PathVariable id: String): Flux<SuggestionDto> = suggestionService.listByTeacher(id)
}