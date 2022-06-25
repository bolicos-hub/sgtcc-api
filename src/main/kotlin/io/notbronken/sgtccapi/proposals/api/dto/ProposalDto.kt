package io.notbronken.sgtccapi.proposals.api.dto

import io.notbronken.sgtccapi.proposals.infra.enumeration.ProposalStatus
import io.notbronken.sgtccapi.students.api.dto.StudentDto
import io.notbronken.sgtccapi.teachers.api.dto.TeacherDto
import java.time.ZonedDateTime

data class ProposalCreateDto(
    val title: String,
    val description: String,
    val authorId: String,
    val leaderId: String,
)

data class ProposalUpdateDto(
    val title: String,
    val description: String,
    val status: ProposalStatus,
)

data class ProposalDto(
    val id: Long,
    val title: String,
    val description: String,
    val author: StudentDto,
    val leader: TeacherDto,
    val createdAt: ZonedDateTime,
)