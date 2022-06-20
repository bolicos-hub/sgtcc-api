package io.notbronken.sgtccapi.teachers.infra.entity

import io.notbronken.sgtccapi.teachers.api.dto.InterestAreaDto
import io.notbronken.sgtccapi.teachers.api.dto.InterestAreaUpdateDto
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "INTEREST_AREAS")
@SequenceGenerator(name = "SEQ_INTEREST_AREA", sequenceName = "SEQUENCE_INTEREST_AREA", allocationSize = 1)
class InterestArea(
    @Id
    @GeneratedValue(generator = "SEQ_INTEREST_AREA", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(nullable = false, length = 200)
    var name: String,
    @Column(nullable = false)
    var description: String,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @ManyToMany(mappedBy = "interestAreas")
    var teachers: Set<Teacher> = setOf(),
) {
    fun toDto() = InterestAreaDto(
        name = name,
        description = description,
        teachers = teachers.map { it.toDto() },
        createdAt = createdAt
    )

    fun update(dto: InterestAreaUpdateDto, teachersDto: Set<Teacher>): InterestArea {
        name = dto.name
        description = dto.description
        teachers = teachersDto

        return this
    }

    fun addTeachers(teachersDto: Set<Teacher>) {
        teachersDto.forEach { teachers.plusElement(it) }
    }
}