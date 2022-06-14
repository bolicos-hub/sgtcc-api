package io.notbronken.sgtccapi.boards.infra.entity

import io.notbronken.sgtccapi.proposals.infra.entity.Proposal
import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
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