package io.notbronken.sgtccapi.semesters.infra.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
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
    val name: String,
    @OneToMany(mappedBy = "semester")
    val classes: Set<Group> = setOf(),
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
}