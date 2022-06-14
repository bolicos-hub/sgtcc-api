package io.notbronken.sgtccapi.teachers.infra.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table
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