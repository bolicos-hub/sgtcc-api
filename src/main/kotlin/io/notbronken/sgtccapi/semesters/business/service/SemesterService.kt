package io.notbronken.sgtccapi.semesters.business.service

import io.notbronken.sgtccapi.common.error.BusinessEntityNotFoundException
import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.semesters.api.dto.SemesterCreateDto
import io.notbronken.sgtccapi.semesters.api.dto.SemesterDto
import io.notbronken.sgtccapi.semesters.api.dto.SemesterUpdateDto
import io.notbronken.sgtccapi.semesters.infra.entity.Semester
import io.notbronken.sgtccapi.semesters.infra.repository.SemesterRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

interface SemesterService {
    fun create(dto: SemesterCreateDto): Mono<CreatedResource>
    fun update(dto: SemesterUpdateDto, id: Long): Mono<SemesterDto>
    fun read(id: Long): Mono<SemesterDto>
    fun delete(id: Long): Mono<DeletedResource>
    fun list(): Flux<SemesterDto>
}

@Service
class SemesterServiceImpl(
    private val semesterRepository: SemesterRepository,
): SemesterService {
    companion object {
        private val LOGGER = Logger.getLogger(SemesterService::class.simpleName)
        private const val CREATE_MESSAGE = "Semester created with unique identifier:"
        private const val UPDATE_MESSAGE = "Semester updated with unique identifier:"
        private const val READ_MESSAGE = "Semester read with unique identifier:"
        private const val LIST_MESSAGE = "Semesters retrieved from the database"
        private const val ENTITY_NOT_FOUND_MESSAGE = "Semester with unique identifier not found:"
    }

    override fun create(dto: SemesterCreateDto): Mono<CreatedResource> {
        val entity = dto.toEntity()
        val response = runBlocking { semesterRepository.save(entity) }

        LOGGER.info("$CREATE_MESSAGE ${response.id}")

        val created = CreatedResource(response.id!!)
        return Mono.just(created)
    }

    override fun update(dto: SemesterUpdateDto, id: Long): Mono<SemesterDto> {
        val entity = runBlocking { semesterRepository.findById(id) }
            .orElseThrow { BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id") }
            .update(dto)
        val response = runBlocking { semesterRepository.save(entity) }

        LOGGER.info("$UPDATE_MESSAGE $response")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun read(id: Long): Mono<SemesterDto> {
        val entity = runBlocking { semesterRepository.findById(id) }.orElseThrow {
            BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id")
        }

        LOGGER.info("$READ_MESSAGE ${entity.id}")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun delete(id: Long): Mono<DeletedResource> {
        TODO("Not yet implemented")
    }

    override fun list(): Flux<SemesterDto> {
        val list = runBlocking { semesterRepository.findAllByOrderByCreatedAtDesc() }

        LOGGER.info(LIST_MESSAGE)

        val converted = list
            .map(Semester::toDto)
            .stream()

        return Flux.fromStream(converted)
    }
}