package io.notbronken.sgtccapi.teachers.business.service

import io.notbronken.sgtccapi.common.error.BusinessEntityNotFoundException
import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.teachers.api.dto.SuggestionCreateDto
import io.notbronken.sgtccapi.teachers.api.dto.SuggestionDto
import io.notbronken.sgtccapi.teachers.api.dto.SuggestionUpdateDto
import io.notbronken.sgtccapi.teachers.infra.entity.Suggestion
import io.notbronken.sgtccapi.teachers.infra.repository.SuggestionRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

interface SuggestionService {
    fun create(dto: SuggestionCreateDto): Mono<CreatedResource>
    fun update(dto: SuggestionUpdateDto, id: Long): Mono<SuggestionDto>
    fun read(id: Long): Mono<SuggestionDto>
    fun delete(id: Long): Mono<Void>
    fun list(): Flux<SuggestionDto>
    fun listByTeacher(teacherId: String): Flux<SuggestionDto>
}

@Service
class SuggestionServiceImpl(
    private val suggestionRepository: SuggestionRepository,
    private val teacherService: TeacherService,
): SuggestionService {
    companion object {
        private val LOGGER = Logger.getLogger(SuggestionService::class.simpleName)
        private const val CREATE_MESSAGE = "Suggestion created with unique identifier:"
        private const val UPDATE_MESSAGE = "Suggestion updated with unique identifier:"
        private const val READ_MESSAGE = "Suggestion read with unique identifier:"
        private const val DELETED_MESSAGE = "Suggestion deleted with unique identifier:"
        private const val LIST_MESSAGE = "Suggestions retrieved from the database"
        private const val ENTITY_NOT_FOUND_MESSAGE = "Suggestion with unique identifier not found:"
    }

    override fun create(dto: SuggestionCreateDto): Mono<CreatedResource> {
        val teacher = teacherService.read(dto.teacherId).block()!!.toEntity()
        val entity = dto.toEntity(teacher)
        val id = runBlocking { suggestionRepository.save(entity) }.id

        LOGGER.info("$CREATE_MESSAGE $id")

        val created = CreatedResource(id!!)
        return Mono.just(created)
    }

    override fun update(dto: SuggestionUpdateDto, id: Long): Mono<SuggestionDto> {
        val entity = runBlocking { suggestionRepository.findById(id) }
            .orElseThrow { BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id") }
            .update(dto)
        val response = runBlocking { suggestionRepository.save(entity) }

        LOGGER.info("$UPDATE_MESSAGE $response")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun read(id: Long): Mono<SuggestionDto> {
        val entity = runBlocking { suggestionRepository.findById(id) }.orElseThrow {
            BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id")
        }

        LOGGER.info("$READ_MESSAGE ${entity.id}")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun delete(id: Long): Mono<Void> {
        val isDeleted = runCatching { suggestionRepository.deleteById(id) }.isSuccess
        LOGGER.info("$DELETED_MESSAGE $id is $isDeleted")
        return Mono.empty()
    }

    override fun list(): Flux<SuggestionDto> {
        val list = runBlocking { suggestionRepository.findAll() }

        LOGGER.info(LIST_MESSAGE)

        val converted = list.map(Suggestion::toDto).stream()
        return Flux.fromStream(converted)
    }

    override fun listByTeacher(teacherId: String): Flux<SuggestionDto> {
        val teacher = teacherService.read(teacherId).block()!!.toEntity()
        val list = runBlocking {
            suggestionRepository
                .findAllByTeacher_Registration(teacher.registration)
                .map { it.toDto() }
        }.stream()
        return Flux.fromStream(list)
    }
}