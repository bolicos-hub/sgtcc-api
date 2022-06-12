package io.notbronken.sgtccapi.students.business.service

import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.students.api.dto.CreateDto
import io.notbronken.sgtccapi.students.api.dto.ListDto
import io.notbronken.sgtccapi.students.infra.entity.Student
import io.notbronken.sgtccapi.students.infra.repository.StudentRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
): StudentService {
    override suspend fun create(dto: CreateDto): CreatedResourceWithId {
        val entity: Student = dto.toEntity()
        val response = runBlocking { studentRepository.save(entity) }
        return CreatedResourceWithId(id = response.registration)
    }

    override suspend fun delete(): DeletedResource {
        TODO("Not yet implemented")
    }

    override suspend fun list(): List<ListDto> {
        val list = studentRepository.findAll()
        return list.map(Student::toDto)
    }

}