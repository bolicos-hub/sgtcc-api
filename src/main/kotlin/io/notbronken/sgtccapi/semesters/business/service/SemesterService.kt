package io.notbronken.sgtccapi.semesters.business.service

import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.semesters.api.dto.CreateDto
import io.notbronken.sgtccapi.semesters.api.dto.ListDto

interface SemesterService {
    fun createSemester(dto: CreateDto): CreatedResource
    fun deleteSemester(): DeletedResource
    fun listSemester(): List<ListDto>
}