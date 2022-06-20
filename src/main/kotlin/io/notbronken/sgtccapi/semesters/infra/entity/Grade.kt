package io.notbronken.sgtccapi.semesters.infra.entity

import io.notbronken.sgtccapi.common.annotation.NoArgCompositeId
import io.notbronken.sgtccapi.semesters.api.dto.GradeDto
import io.notbronken.sgtccapi.students.infra.entity.Student
import java.io.Serializable
import java.util.Objects
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
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
    @Column(scale = 2, precision = 4)
    var note: Double? = null,
) {
    fun toDto() = GradeDto(
        registration = student.registration,
        name = student.name,
        email = student.email,
        phone = student.phone,
        status = student.status,
        createdAt = student.createdAt,
        note = note,
    )

    fun addNote(notes: Double) {
        note = notes
    }

    override fun equals(other: Any?): Boolean =
        other is Grade
            && other.student.registration == student.registration
            && other.group.id == group.id

    override fun hashCode(): Int {
        val studentHashCode = Objects.hashCode(student.registration)
        val groupHashCode = Objects.hashCode(group.id)
        return Objects.hash(studentHashCode, groupHashCode)
    }
}

@NoArgCompositeId
class GradeCompositeId(
    val student: String? = null,
    val group: Long? = null,
): Serializable {
    override fun equals(other: Any?): Boolean =
        other is Grade
                && other.student.registration == student
                && other.group.id == group


    override fun hashCode(): Int {
        val studentHashCode = Objects.hashCode(student)
        val groupHashCode = Objects.hashCode(group)
        return Objects.hash(studentHashCode, groupHashCode)
    }
}