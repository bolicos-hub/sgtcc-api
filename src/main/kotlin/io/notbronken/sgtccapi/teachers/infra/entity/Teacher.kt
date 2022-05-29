package io.notbronken.sgtccapi.teachers.infra.entity

import io.notbronken.sgtccapi.boards.infra.entity.Board
import io.notbronken.sgtccapi.proposals.infra.entity.Examination
import io.notbronken.sgtccapi.proposals.infra.entity.Proposal
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.ZonedDateTime
import io.notbronken.sgtccapi.semesters.infra.entity.Class as Group

@Entity
@Table(name = "TEACHERS")
class Teacher (
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
    @Column(nullable = false, length = 50)
    val lattes: String,
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

}