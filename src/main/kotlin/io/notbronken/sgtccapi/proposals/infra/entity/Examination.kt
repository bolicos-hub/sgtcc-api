package io.notbronken.sgtccapi.proposals.infra.entity

import io.notbronken.sgtccapi.proposals.infra.enumeration.ExaminationType
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
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "EXAMINATIONS")
@SequenceGenerator(name = "SEQ_EXAMINATION", sequenceName = "SEQUENCE_EXAMINATION", allocationSize = 1)
class Examination(
    @Id
    @GeneratedValue(generator = "SEQ_EXAMINATION", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: ExaminationType,
    @Column(nullable = false, scale = 2, precision = 4)
    val note: Double,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false)
    val presentation: String,
    @Column(nullable = false, name = "TEXT_CONTENT")
    val textContent: String,
    @Column(nullable = false, name = "TEXT_STRUCTURE")
    val textStructure: String,
    @Column(nullable = false)
    val language: String,
    @Column(nullable = false)
    val observation: String,
    @Column(nullable = false)
    val relevance: String,
    @ManyToOne
    @JoinColumn(name = "FK_PROPOSAL_PK", nullable = false)
    val proposal: Proposal,
    @ManyToOne
    @JoinColumn(name = "FK_TEACHER_PK", nullable = false)
    val teacher: Teacher,
) {
}