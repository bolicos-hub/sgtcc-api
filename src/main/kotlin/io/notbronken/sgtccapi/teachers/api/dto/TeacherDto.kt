package io.notbronken.sgtccapi.teachers.api.dto

import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import java.time.ZonedDateTime

data class TeacherCreateDto(
    val registration: String,
    val name: String,
    val email: String,
    val phone: String,
    val lattes: String,
) {
    fun toEntity() = Teacher(
        registration = registration,
        name = name,
        email = email,
        phone = phone,
        lattes = lattes
    )
}

data class TeacherUpdateDto(
    val name: String,
    val email: String,
    val phone: String,
    val lattes: String,
)

data class TeacherDto(
    val registration: String,
    val name: String,
    val email: String,
    val phone: String,
    val lattes: String,
    val createdAt: ZonedDateTime,
) {
    fun toEntity() = Teacher(
        registration = registration,
        name = name,
        email = email,
        phone = phone,
        lattes = lattes,
        createdAt = createdAt,
    )
}