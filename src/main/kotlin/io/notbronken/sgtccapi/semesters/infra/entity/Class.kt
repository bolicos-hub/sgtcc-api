package io.notbronken.sgtccapi.semesters.infra.entity

import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "CLASSES")
@SequenceGenerator(name = "SEQ_CLASS", sequenceName = "SEQUENCE_CLASS", allocationSize = 1)
class Class (
    @Id
    @GeneratedValue(generator = "SEQ_CLASS", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long?,
    @Column(nullable = false, length = 50)
    val name: String,
    @ManyToOne
    @JoinColumn(name = "FK_SEMESTER_PK", nullable = false)
    val semester: Semester,
    @ManyToOne
    @JoinColumn(name = "FK_TEACHER_PK", nullable = false)
    val teacher: Teacher,
    @OneToMany(mappedBy = "group")
    val students: Set<Grade> = setOf(),
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {

}