package io.notbronken.sgtccapi.teachers.api.dto

import io.notbronken.sgtccapi.teachers.infra.entity.Suggestion
import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import io.notbronken.sgtccapi.teachers.infra.enumeration.SuggestionStatus
import java.time.ZonedDateTime

data class SuggestionCreateDto(
    val title: String,
    val description: String,
    val search: Boolean,
    val project: String,
    val teacherId: String,
) {
    fun toEntity(teacher: Teacher) = Suggestion(
        title = title,
        description = description,
        search = search,
        project = project,
        teacher = teacher,
    )
}

data class SuggestionUpdateDto(
    val title: String,
    val description: String,
    val search: Boolean,
    val project: String,
    val status: SuggestionStatus,
)

data class SuggestionDto(
    val id: Long,
    val title: String,
    val description: String,
    val search: Boolean,
    val project: String,
    val status: SuggestionStatus,
    val createdAt: ZonedDateTime,
    val teacherId: String,
) {
    fun toEntity() = Suggestion(
        id = id,
        title = title,
        description = description,
        search = search,
        project = project,
        status = status,
        createdAt = createdAt,
    )
}