package io.notbronken.sgtccapi.semesters.infra.entity

import io.notbronken.sgtccapi.semesters.api.dto.SemesterDto
import io.notbronken.sgtccapi.semesters.api.dto.SemesterUpdateDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import java.time.ZonedDateTime
import io.notbronken.sgtccapi.semesters.infra.entity.Class as Group

@Entity
@Table(name = "SEMESTERS")
@SequenceGenerator(name = "SEQ_SEMESTER", sequenceName = "SEQUENCE_SEMESTER", allocationSize = 1)
class Semester(
    @Id
    @GeneratedValue(generator = "SEQ_SEMESTER", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(nullable = false, length = 50)
    var name: String,
    @OneToMany(mappedBy = "semester")
    var classes: Set<Group> = setOf(),
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
    fun toDto() = SemesterDto(
        id = id!!,
        name = name,
        createdAt = createdAt,
    )

    fun update(dto: SemesterUpdateDto): Semester {
        name = dto.name
//        classes = dto.classes.map { Group() }

        return this
    }
}