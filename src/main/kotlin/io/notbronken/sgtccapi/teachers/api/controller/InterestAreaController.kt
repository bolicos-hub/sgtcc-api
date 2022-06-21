package io.notbronken.sgtccapi.teachers.api.controller

import io.notbronken.sgtccapi.common.annotation.MappingJson
import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.teachers.api.dto.InterestAreaCreateDto
import io.notbronken.sgtccapi.teachers.api.dto.InterestAreaDto
import io.notbronken.sgtccapi.teachers.api.dto.InterestAreaUpdateDto
import io.notbronken.sgtccapi.teachers.business.service.InterestAreaService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

@RestController
@RequestMapping("/api/interest-areas")
class InterestAreaController(
    val interestAreaService: InterestAreaService,
) {
    companion object {
        private val LOGGER = Logger.getLogger(InterestAreaController::class.simpleName)
    }

    @MappingJson(method = [RequestMethod.POST], code = HttpStatus.CREATED)
    fun create(@RequestBody dto: InterestAreaCreateDto): Mono<CreatedResource> = interestAreaService.create(dto)

    @MappingJson(value = ["/{id}"], method = [RequestMethod.PUT])
    fun update(
        @RequestBody dto: InterestAreaUpdateDto, @PathVariable id: Long
    ): Mono<InterestAreaDto> = interestAreaService.update(dto, id)

    @MappingJson(value = ["/{id}"])
    fun findById(@PathVariable id: Long): Mono<InterestAreaDto> = interestAreaService.read(id)

    @MappingJson(value = ["/{id}"], method = [RequestMethod.DELETE], code = HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long): Mono<Void> = interestAreaService.delete(id)

    @MappingJson
    fun findAll(): Flux<InterestAreaDto> = interestAreaService.list()

    @MappingJson(value = ["/teaches/{id}"])
    fun findAllByTeacherId(@PathVariable id: String): Flux<InterestAreaDto> = interestAreaService.listByTeacher(id)
}