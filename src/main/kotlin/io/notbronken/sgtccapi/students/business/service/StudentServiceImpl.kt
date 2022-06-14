package io.notbronken.sgtccapi.students.business.service

import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.students.api.dto.CreateDto
import io.notbronken.sgtccapi.students.api.dto.ListDto
import io.notbronken.sgtccapi.students.infra.entity.Student
import io.notbronken.sgtccapi.students.infra.repository.StudentRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
): StudentService {
    companion object {
        private val LOGGER = Logger.getLogger(this::class.simpleName)
    }

    override fun create(dto: CreateDto): Mono<CreatedResourceWithId> {
        val entity: Student = dto.toEntity()
        val response = runBlocking { studentRepository.save(entity) }
        val created = CreatedResourceWithId(id = response.registration)
        return Mono.just(created)
    }

    override fun delete(): Mono<DeletedResource> {
        TODO("Not yet implemented")
    }

    override fun list(): Flux<List<ListDto>> {
        val list = runBlocking { studentRepository.findAll() }
        val converted = list.map(Student::toDto)
        return Flux.just(converted)
    }

}