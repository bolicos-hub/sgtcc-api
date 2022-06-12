package io.notbronken.sgtccapi.students.api.dto

import io.notbronken.sgtccapi.students.infra.entity.Student
import io.notbronken.sgtccapi.students.infra.enumeration.StudentStatus
import java.time.ZonedDateTime

data class CreateDto(
    val registration: String,
    val name: String,
    val email: String,
    val phone: String,
    val cpf: String,
) {
    fun toEntity() = Student(registration = registration, name = name, email = email, phone = phone, cpf = cpf)
}

data class ListDto(
    val registration: String,
    val name: String,
    val email: String,
    val phone: String,
    val cpf: String,
    val status: StudentStatus,
    val createdAt: ZonedDateTime,
) {
    fun toEntity() = Student(
        registration = registration,
        name = name,
        email = email,
        phone = phone,
        cpf = cpf,
        status = status,
        createdAt = createdAt,
    )
}