package io.notbronken.sgtccapi.students.business.service

import io.notbronken.sgtccapi.common.error.BusinessEntityNotFoundException
import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.students.api.dto.StudentCreateDto
import io.notbronken.sgtccapi.students.api.dto.StudentDto
import io.notbronken.sgtccapi.students.api.dto.StudentUpdateDto
import io.notbronken.sgtccapi.students.infra.entity.Student
import io.notbronken.sgtccapi.students.infra.repository.StudentRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

interface StudentService {
    fun create(dto: StudentCreateDto): Mono<CreatedResourceWithId>
    fun update(dto: StudentUpdateDto, id: String): Mono<StudentDto>
    fun read(id: String): Mono<StudentDto>
    fun delete(id: String): Mono<DeletedResource>
    fun list(): Flux<StudentDto>
    fun list(ids: List<String>): Flux<StudentDto>
}

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
): StudentService {
    companion object {
        private val LOGGER = Logger.getLogger(StudentService::class.simpleName)
        private const val CREATE_MESSAGE = "Student created with unique identifier:"
        private const val UPDATE_MESSAGE = "Student updated with unique identifier:"
        private const val READ_MESSAGE = "Student read with unique identifier:"
        private const val LIST_MESSAGE = "Students retrieved from the database"
        private const val ENTITY_NOT_FOUND_MESSAGE = "Student with unique identifier not found:"
    }

    override fun create(dto: StudentCreateDto): Mono<CreatedResourceWithId> {
        val entity = dto.toEntity()
        val response = runBlocking { studentRepository.save(entity) }

        LOGGER.info("$CREATE_MESSAGE ${response.registration}")

        val created = CreatedResourceWithId(id = response.registration)
        return Mono.just(created)
    }

    override fun update(dto: StudentUpdateDto, id: String): Mono<StudentDto> {
        val entity = runBlocking { studentRepository.findById(id) }
            .orElseThrow { BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id") }
            .update(dto)
        val response = runBlocking { studentRepository.save(entity) }

        LOGGER.info("$UPDATE_MESSAGE $response")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun read(id: String): Mono<StudentDto> {
        val entity = runBlocking { studentRepository.findById(id) }.orElseThrow {
            BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id")
        }

        LOGGER.info("$READ_MESSAGE ${entity.registration}")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun delete(id: String): Mono<DeletedResource> {
        TODO("Not yet implemented")
    }

    override fun list(): Flux<StudentDto> {
        val list = runBlocking { studentRepository.findAllByOrderByCreatedAtDesc() }

        LOGGER.info(LIST_MESSAGE)

        val converted = list.map(Student::toDto).stream()
        return Flux.fromStream(converted)
    }

    override fun list(ids: List<String>): Flux<StudentDto> {
        val list = runBlocking { studentRepository.findAllById(ids) }

        LOGGER.info(LIST_MESSAGE)

        val converted = list.map(Student::toDto).stream()
        return Flux.fromStream(converted)
    }

}