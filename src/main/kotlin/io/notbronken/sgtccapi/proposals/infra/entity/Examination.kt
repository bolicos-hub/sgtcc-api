package io.notbronken.sgtccapi.proposals.infra.entity

import io.notbronken.sgtccapi.proposals.infra.enumeration.ExaminationType
import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
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