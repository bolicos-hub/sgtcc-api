package io.notbronken.sgtccapi.students.infra.entity

import io.notbronken.sgtccapi.proposals.infra.entity.Proposal
import io.notbronken.sgtccapi.semesters.infra.entity.Grade
import io.notbronken.sgtccapi.students.api.dto.ListDto
import io.notbronken.sgtccapi.students.infra.enumeration.StudentStatus
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "STUDENTS")
class Student(
    @Id
    @Column(nullable = false, length = 20, unique = true)
    val registration: String,
    @Column(nullable = false, length = 50)
    val name: String,
    @Column(nullable = false, length = 50, unique = true)
    val email: String,
    @Column(nullable = false, length = 11, unique = true)
    val phone: String,
    @Column(nullable = false, length = 11, unique = true)
    val cpf: String,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @OneToMany(mappedBy = "student")
    val classes: Set<Grade> = setOf(),
    @OneToMany(mappedBy = "author")
    val proposals: Set<Proposal> = setOf(),
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: StudentStatus = StudentStatus.ACTIVE,
) {
    fun toDto() = ListDto(
        registration = registration,
        name = name,
        email = email,
        phone = phone,
        cpf = cpf,
        status = status,
        createdAt = createdAt,
    )
}