package io.notbronken.sgtccapi.teachers.business.service

import io.notbronken.sgtccapi.common.error.BusinessEntityNotFoundException
import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.teachers.api.dto.InterestAreaCreateDto
import io.notbronken.sgtccapi.teachers.api.dto.InterestAreaDto
import io.notbronken.sgtccapi.teachers.api.dto.InterestAreaUpdateDto
import io.notbronken.sgtccapi.teachers.infra.entity.InterestArea
import io.notbronken.sgtccapi.teachers.infra.repository.InterestAreaRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

interface InterestAreaService {
    fun create(dto: InterestAreaCreateDto): Mono<CreatedResource>
    fun update(dto: InterestAreaUpdateDto, id: Long): Mono<InterestAreaDto>
    fun read(id: Long): Mono<InterestAreaDto>
    fun delete(id: Long): Mono<Void>
    fun list(): Flux<InterestAreaDto>
    fun listByTeacher(teacherId: String): Flux<InterestAreaDto>
}

@Service
class InterestAreaServiceImpl(
    private val interestAreaRepository: InterestAreaRepository,
    private val teacherService: TeacherService,
): InterestAreaService {
    companion object {
        private val LOGGER = Logger.getLogger(InterestAreaService::class.simpleName)
        private const val CREATE_MESSAGE = "InterestArea created with unique identifier:"
        private const val UPDATE_MESSAGE = "InterestArea updated with unique identifier:"
        private const val READ_MESSAGE = "InterestArea read with unique identifier:"
        private const val DELETED_MESSAGE = "InterestArea deleted with unique identifier:"
        private const val LIST_MESSAGE = "InterestAreas retrieved from the database"
        private const val ENTITY_NOT_FOUND_MESSAGE = "InterestArea with unique identifier not found:"
    }

    override fun create(dto: InterestAreaCreateDto): Mono<CreatedResource> {
        val entity = dto.toEntity()
        val id = runBlocking { interestAreaRepository.save(entity) }.id

        LOGGER.info("$CREATE_MESSAGE $id")

        val created = CreatedResource(id!!)
        return Mono.just(created)
    }

    override fun update(dto: InterestAreaUpdateDto, id: Long): Mono<InterestAreaDto> {
        val teachers = runBlocking { teacherService.list() }
        val entity = runBlocking { interestAreaRepository.findById(id) }
            .orElseThrow { BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id") }
            .update(dto)
        val response = runBlocking { interestAreaRepository.save(entity) }

        LOGGER.info("$UPDATE_MESSAGE $response")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun read(id: Long): Mono<InterestAreaDto> {
        val entity = runBlocking { interestAreaRepository.findById(id) }.orElseThrow {
            BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id")
        }

        LOGGER.info("$READ_MESSAGE ${entity.id}")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun delete(id: Long): Mono<Void> {
        val isDeleted = runCatching { interestAreaRepository.deleteById(id) }.isSuccess
        LOGGER.info("$DELETED_MESSAGE $id is $isDeleted")
        return Mono.empty()
    }

    override fun list(): Flux<InterestAreaDto> {
        val list = runBlocking { interestAreaRepository.findAll() }

        LOGGER.info(LIST_MESSAGE)

        val converted = list.map(InterestArea::toDto).stream()
        return Flux.fromStream(converted)
    }

    override fun listByTeacher(teacherId: String): Flux<InterestAreaDto> {
        val teacher = teacherService.read(teacherId).block()!!.toEntity()
        val list = runBlocking {
            interestAreaRepository
                .findAllByTeacher_Registration(teacher.registration)
                .map { it.toDto() }
        }.stream()
        return Flux.fromStream(list)
    }
}