package io.notbronken.sgtccapi.teachers.infra.entity

import io.notbronken.sgtccapi.boards.infra.entity.Board
import io.notbronken.sgtccapi.proposals.infra.entity.Examination
import io.notbronken.sgtccapi.proposals.infra.entity.Proposal
import io.notbronken.sgtccapi.teachers.api.dto.TeacherDto
import io.notbronken.sgtccapi.teachers.api.dto.TeacherUpdateDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table
import java.time.ZonedDateTime
import io.notbronken.sgtccapi.semesters.infra.entity.Class as Group

@Entity
@Table(name = "TEACHERS")
class Teacher (
    @Id
    @Column(nullable = false, length = 20, unique = true)
    var registration: String,
    @Column(nullable = false, length = 50)
    var name: String,
    @Column(nullable = false, length = 50, unique = true)
    var email: String,
    @Column(nullable = false, length = 11, unique = true)
    var phone: String,
    @Column(nullable = false, length = 50)
    var lattes: String,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @OneToMany(mappedBy = "teacher")
    val classes: Set<Group> = setOf(),
    @OneToMany(mappedBy = "leader")
    val proposals: Set<Proposal> = setOf(),
    @OneToMany(mappedBy = "teacher")
    val examinations: Set<Examination> = setOf(),
    @ManyToMany
    @JoinTable(
        name = "TEACHERS_BOARDS",
        joinColumns = [JoinColumn(name = "FK_TEACHER_PK", nullable = false)],
        inverseJoinColumns = [JoinColumn(name = "FK_BOARD_PK", nullable = false)]
    )
    val boards: Set<Board> = setOf(),
    @ManyToMany
    @JoinTable(
        name = "TEACHERS_INTEREST_AREAS",
        joinColumns = [JoinColumn(name = "FK_TEACHER_PK", nullable = false)],
        inverseJoinColumns = [JoinColumn(name = "FK_INTEREST_AREA_PK", nullable = false)]
    )
    val interestAreas: Set<InterestArea> = setOf(),
    @OneToMany(mappedBy = "teacher")
    val suggestions: Set<Suggestion> = setOf(),
) {
    fun toDto() = TeacherDto(
        registration = registration,
        name = name,
        email = email,
        phone = phone,
        lattes = lattes,
        createdAt = createdAt,
    )

    fun update(dto: TeacherUpdateDto): Teacher {
        name = dto.name
        email = dto.email
        phone = dto.phone
        lattes = dto.lattes

        return this
    }
}