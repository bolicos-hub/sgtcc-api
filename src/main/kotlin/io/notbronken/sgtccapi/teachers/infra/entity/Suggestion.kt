package io.notbronken.sgtccapi.teachers.infra.entity

import io.notbronken.sgtccapi.teachers.infra.enumeration.SuggestionStatus
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
@Table(name = "SUGGESTIONS")
@SequenceGenerator(name = "SEQ_SUGGESTION", sequenceName = "SEQUENCE_SUGGESTION", allocationSize = 1)
class Suggestion(
    @Id
    @GeneratedValue(generator = "SEQ_SUGGESTION", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(nullable = false, length = 200)
    val title: String,
    @Column(nullable = false)
    val description: String,
    @Column(nullable = false)
    val search: Boolean,
    @Column
    val project: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: SuggestionStatus,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "FK_TEACHER_PK", nullable = false)
    val teacher: Teacher,
) {
}