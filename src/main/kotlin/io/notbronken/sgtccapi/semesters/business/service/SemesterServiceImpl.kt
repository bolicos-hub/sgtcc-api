package io.notbronken.sgtccapi.semesters.business.service

import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.semesters.api.dto.CreateDto
import io.notbronken.sgtccapi.semesters.api.dto.ListDto
import io.notbronken.sgtccapi.semesters.infra.entity.Semester
import io.notbronken.sgtccapi.semesters.infra.repository.SemesterRepository
import org.springframework.stereotype.Service

@Service
class SemesterServiceImpl(
    private val semesterRepository: SemesterRepository,
): SemesterService {

    override fun createSemester(dto: CreateDto): CreatedResource {
        val entity: Semester = dto.toEntity()
        val response = semesterRepository.save(entity)
        return CreatedResource(id = response.id!!)
    }

    override fun deleteSemester(): DeletedResource {
        TODO("Not yet implemented")
    }

    override fun listSemester(): List<ListDto> {
        TODO("Not yet implemented")
    }

}