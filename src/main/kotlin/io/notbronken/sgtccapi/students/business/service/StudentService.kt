package io.notbronken.sgtccapi.students.business.service

import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.students.api.dto.CreateDto
import io.notbronken.sgtccapi.students.api.dto.ListDto

interface StudentService {
    suspend fun create(dto: CreateDto): CreatedResourceWithId
    suspend fun delete(): DeletedResource
    suspend fun list(): List<ListDto>
}