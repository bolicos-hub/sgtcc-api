package io.notbronken.sgtccapi.semesters.infra.entity

import io.notbronken.sgtccapi.proposals.infra.entity.Proposal
import io.notbronken.sgtccapi.students.infra.entity.Student
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.io.Serializable
import io.notbronken.sgtccapi.semesters.infra.entity.Class as Group

@Entity
@IdClass(GradeCompositeId::class)
@Table(name = "CLASSES_STUDENTS")
class Grade(
    @Id
    @ManyToOne
    @JoinColumn(name = "FK_STUDENT_PK", nullable = false)
    val student: Student,
    @Id
    @ManyToOne
    @JoinColumn(name = "FK_CLASS_PK", nullable = false)
    val group: Group,
    @Column(nullable = false, scale = 2, precision = 4)
    val note: Double,
    @ManyToOne
    @JoinColumn(name = "FK_PROPOSAL_PK", nullable = false)
    val proposal: Proposal,
) {
    override fun equals(other: Any?): Boolean =
        other is Grade
            && other.student.registration == student.registration
            && other.group.id == group.id
            && other.proposal.id == proposal.id

    override fun hashCode(): Int =
        student.registration.hashCode() + group.id.hashCode() + proposal.id.hashCode()
}

class GradeCompositeId(
    private val student: Student,
    private val group: Group,
): Serializable {
    override fun equals(other: Any?): Boolean =
        other is Grade
                && other.student.registration == student.registration
                && other.group.id == group.id

    override fun hashCode(): Int = student.registration.hashCode() + group.id.hashCode()
}