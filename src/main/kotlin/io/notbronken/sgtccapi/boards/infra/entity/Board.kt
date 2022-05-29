package io.notbronken.sgtccapi.boards.infra.entity

import io.notbronken.sgtccapi.proposals.infra.entity.Proposal
import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "BOARDS")
@SequenceGenerator(name = "SEQ_BOARD", sequenceName = "SEQUENCE_BOARD", allocationSize = 1)
class Board(
    @Id
    @GeneratedValue(generator = "SEQ_BOARD", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @OneToOne(optional = false)
    @JoinColumn(name = "FK_PROPOSAL_PK", unique = true, nullable = false, updatable = false)
    val proposal: Proposal,
    @ManyToMany(mappedBy = "boards")
    val evaluators: Set<Teacher> = setOf(),
) {
}