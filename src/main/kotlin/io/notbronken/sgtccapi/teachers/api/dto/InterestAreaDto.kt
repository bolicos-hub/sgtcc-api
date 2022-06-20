package io.notbronken.sgtccapi.teachers.api.dto

import io.notbronken.sgtccapi.teachers.infra.entity.InterestArea
import java.time.ZonedDateTime

data class InterestAreaCreateDto(
    val name: String,
    val description: String,
) {
    fun toEntity() = InterestArea(
        name = name,
        description = description,
    )
}

data class InterestAreaUpdateDto(
    val name: String,
    val description: String,
    val teachers: List<String>,
)

data class InterestAreaDto(
    val name: String,
    val description: String,
    val teachers: List<TeacherDto>,
    val createdAt: ZonedDateTime,
) {
    fun toEntity() = InterestArea(
        name = name,
        description = description,
        teachers = teachers.map { it.toEntity() }.toSet(),
        createdAt = createdAt,
    )
}