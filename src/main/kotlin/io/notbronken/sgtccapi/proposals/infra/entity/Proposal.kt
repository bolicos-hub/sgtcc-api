package io.notbronken.sgtccapi.proposals.infra.entity

import io.notbronken.sgtccapi.boards.infra.entity.Board
import io.notbronken.sgtccapi.proposals.infra.enumeration.ProposalStatus
import io.notbronken.sgtccapi.semesters.infra.entity.Grade
import io.notbronken.sgtccapi.students.infra.entity.Student
import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "PROPOSALS")
@SequenceGenerator(name = "SEQ_PROPOSAL", sequenceName = "SEQUENCE_PROPOSAL", allocationSize = 1)
class Proposal(
    @Id
    @GeneratedValue(generator = "SEQ_PROPOSAL", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(nullable = false, length = 200)
    val title: String,
    @Column(nullable = false)
    val description: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: ProposalStatus,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "FK_STUDENT_PK", nullable = false)
    val author: Student,
    @ManyToOne
    @JoinColumn(name = "FK_TEACHER_PK", nullable = false)
    val leader: Teacher,
    @OneToMany(mappedBy = "proposal")
    val documents: Set<Document> = setOf(),
    @OneToMany(mappedBy = "proposal")
    val examinations: Set<Examination> = setOf(),
    @OneToOne(mappedBy = "proposal")
    val board: Board,
    @OneToMany(mappedBy = "proposal")
    val classes: Set<Grade> = setOf(),
) {
}