package io.notbronken.sgtccapi.students.infra.entity

import io.notbronken.sgtccapi.proposals.infra.entity.Proposal
import io.notbronken.sgtccapi.semesters.infra.entity.Grade
import io.notbronken.sgtccapi.students.api.dto.StudentDto
import io.notbronken.sgtccapi.students.api.dto.StudentUpdateDto
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
    var name: String,
    @Column(nullable = false, length = 50, unique = true)
    var email: String,
    @Column(nullable = false, length = 11, unique = true)
    var phone: String,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @OneToMany(mappedBy = "student")
    val classes: Set<Grade> = setOf(),
    @OneToMany(mappedBy = "author")
    val proposals: Set<Proposal> = setOf(),
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: StudentStatus = StudentStatus.ACTIVE,
) {
    fun toDto() = StudentDto(
        registration = registration,
        name = name,
        email = email,
        phone = phone,
        status = status,
        createdAt = createdAt,
    )

    fun update(dto: StudentUpdateDto): Student {
        name = dto.name
        email = dto.email
        phone = dto.phone
        status = dto.status

        return this
    }
}