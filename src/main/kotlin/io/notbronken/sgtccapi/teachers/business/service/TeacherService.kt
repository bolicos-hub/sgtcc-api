package io.notbronken.sgtccapi.teachers.business.service

import io.notbronken.sgtccapi.common.error.BusinessEntityNotFoundException
import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.teachers.api.dto.TeacherCreateDto
import io.notbronken.sgtccapi.teachers.api.dto.TeacherDto
import io.notbronken.sgtccapi.teachers.api.dto.TeacherUpdateDto
import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import io.notbronken.sgtccapi.teachers.infra.repository.TeacherRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

interface TeacherService {
    fun create(dto: TeacherCreateDto): Mono<CreatedResourceWithId>
    fun update(dto: TeacherUpdateDto, id: String): Mono<TeacherDto>
    fun read(id: String): Mono<TeacherDto>
    fun delete(id: String): Mono<DeletedResource>
    fun list(): Flux<TeacherDto>
}

@Service
class TeacherServiceImpl(
    private val teacherRepository: TeacherRepository
): TeacherService {
    companion object {
        private val LOGGER = Logger.getLogger(TeacherService::class.simpleName)
        private const val CREATE_MESSAGE = "Teacher created with unique identifier:"
        private const val UPDATE_MESSAGE = "Teacher updated with unique identifier:"
        private const val READ_MESSAGE = "Teacher read with unique identifier:"
        private const val LIST_MESSAGE = "Teachers retrieved from the database"
        private const val ENTITY_NOT_FOUND_MESSAGE = "Teacher with unique identifier not found:"
    }
    override fun create(dto: TeacherCreateDto): Mono<CreatedResourceWithId> {
        val entity = dto.toEntity()
        val response = runBlocking { teacherRepository.save(entity) }

        LOGGER.info("$CREATE_MESSAGE ${response.registration}")

        val created = CreatedResourceWithId(id = response.registration)
        return Mono.just(created)
    }

    override fun update(dto: TeacherUpdateDto, id: String): Mono<TeacherDto> {
        val entity = runBlocking { teacherRepository.findById(id) }
            .orElseThrow { BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id") }
            .update(dto)
        val response = runBlocking { teacherRepository.save(entity) }

        LOGGER.info("$UPDATE_MESSAGE $response")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun read(id: String): Mono<TeacherDto> {
        val entity = runBlocking { teacherRepository.findById(id) }.orElseThrow {
            BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id")
        }

        LOGGER.info("$READ_MESSAGE ${entity.registration}")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun delete(id: String): Mono<DeletedResource> {
        TODO("Not yet implemented")
    }

    override fun list(): Flux<TeacherDto> {
        val list = runBlocking { teacherRepository.findAll() }

        LOGGER.info(LIST_MESSAGE)

        val converted = list.map(Teacher::toDto).stream()
        return Flux.fromStream(converted)
    }

}