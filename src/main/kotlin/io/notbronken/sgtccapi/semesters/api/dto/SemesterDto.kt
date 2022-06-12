package io.notbronken.sgtccapi.semesters.api.dto

import io.notbronken.sgtccapi.semesters.infra.entity.Semester
import java.time.ZonedDateTime

data class CreateDto(val name: String) {
    fun toEntity() = Semester(name = name)
}

data class ListDto(val id: Long, val name: String, val createdAt: ZonedDateTime) {
    fun toEntity() = Semester(id = id, name = name, createdAt = createdAt)
}
