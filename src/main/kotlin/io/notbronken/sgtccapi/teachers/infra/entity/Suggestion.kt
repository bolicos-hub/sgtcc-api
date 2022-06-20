package io.notbronken.sgtccapi.teachers.infra.entity

import io.notbronken.sgtccapi.teachers.api.dto.SuggestionDto
import io.notbronken.sgtccapi.teachers.api.dto.SuggestionUpdateDto
import io.notbronken.sgtccapi.teachers.infra.enumeration.SuggestionStatus
import java.time.ZonedDateTime
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

@Entity
@Table(name = "SUGGESTIONS")
@SequenceGenerator(name = "SEQ_SUGGESTION", sequenceName = "SEQUENCE_SUGGESTION", allocationSize = 1)
class Suggestion(
    @Id
    @GeneratedValue(generator = "SEQ_SUGGESTION", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(nullable = false, length = 200)
    var title: String,
    @Column(nullable = false)
    var description: String,
    @Column(nullable = false)
    var search: Boolean,
    @Column
    var project: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: SuggestionStatus = SuggestionStatus.FREE,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "FK_TEACHER_PK", nullable = false)
    var teacher: Teacher? = null,
) {
    fun toDto() = SuggestionDto(
        id = id!!,
        title = title,
        description = description,
        search = search,
        project = project,
        status = status,
        createdAt = createdAt,
        teacherId = teacher!!.registration
    )

    fun update(dto: SuggestionUpdateDto): Suggestion {
        title = dto.title
        description = dto.description
        search = dto.search
        project = dto.project
        status = dto.status

        return this
    }
    fun addTeacher(entity: Teacher) {
        teacher = entity
    }
}