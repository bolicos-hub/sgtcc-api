package io.notbronken.sgtccapi.teachers.infra.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "INTEREST_AREAS")
@SequenceGenerator(name = "SEQ_INTEREST_AREA", sequenceName = "SEQUENCE_INTEREST_AREA", allocationSize = 1)
class InterestArea(
    @Id
    @GeneratedValue(generator = "SEQ_INTEREST_AREA", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(nullable = false, length = 200)
    val name: String,
    @Column(nullable = false)
    val description: String,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @ManyToMany(mappedBy = "interestAreas")
    val teachers: Set<Teacher> = setOf(),
) {
}